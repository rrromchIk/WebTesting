package com.epam.testing.util;

import com.epam.testing.model.entity.Question;
import com.epam.testing.model.entity.QuestionType;
import com.epam.testing.model.service.TestQuestionService;
import com.epam.testing.model.service.UserAnswerService;

import java.util.List;

public class CalculateTestResultService {
    private static final TestQuestionService testQuestionService = new TestQuestionService();
    private static final UserAnswerService userAnswerService = new UserAnswerService();

    private CalculateTestResultService() {
    }

    public static float getTestResult(long testId, long userId) {
        List<Question> questions = testQuestionService.getQuestionsByTestId(testId);
        int totalMaxScore = getTotalMaxScore(questions);
        int actualScore = getActualScore(questions, userId);
        return (actualScore * 100) / (float)(totalMaxScore);
    }

    private static int getTotalMaxScore(List<Question> questions) {
        int totalScore = 0;
        for (Question question : questions) {
            totalScore += question.getMaxScore();
        }
        return totalScore;
    }

    private static int getActualScore(List<Question> questions, long userId) {
        int actualScore = 0;
        List<String> questionCorrectAnswers;
        List<String> userAnswers;

        for (Question question : questions) {
            long questionId = question.getId();
            questionCorrectAnswers = testQuestionService
                    .getCorrectAnswers(questionId);
            userAnswers = userAnswerService
                    .getUsersAnswers(userId, questionId);

            if (question.getType().equals(QuestionType.SINGLE_CORRECT_ANSWER)) {
                actualScore += calculateScoreOfSingleAnswerQuestion(question.getMaxScore(),
                        questionCorrectAnswers, userAnswers);
            } else {
                actualScore += calculateScoreOfMultipleAnswerQuestion(question.getMaxScore(),
                        questionCorrectAnswers, userAnswers);
            }
        }


        return actualScore;
    }

    private static int calculateScoreOfSingleAnswerQuestion(int maxScore,
                                                            List<String> questionCorrectAnswers,
                                                            List<String> userAnswers) {
        int score = 0;
        if(!userAnswers.isEmpty()) {
            score += questionCorrectAnswers.contains(userAnswers.get(0)) ? maxScore : 0;
        }
        return score;
    }

    private static int calculateScoreOfMultipleAnswerQuestion(int maxScore,
                                                              List<String> questionCorrectAnswers,
                                                            List<String> userAnswers) {
        int score = 0;
        int amountOfCorrectAnswers = questionCorrectAnswers.size();
        int amountOfUserCorrectAnswers = 0;
        for(String userAnswer : userAnswers) {
            if(questionCorrectAnswers.contains(userAnswer)) {
                amountOfUserCorrectAnswers++;
            } else {
                amountOfUserCorrectAnswers = 0;
                break;
            }
        }

        if(amountOfUserCorrectAnswers == amountOfCorrectAnswers) {
            score += maxScore;
        }

        return score;
    }
}
