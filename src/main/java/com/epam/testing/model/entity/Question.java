package com.epam.testing.model.entity;

import java.util.Objects;

/**
 * Entity for question
 *
 * @author rom4ik
 */

public class Question extends Entity {
    private String text;
    private final QuestionType type;
    private final int maxScore;
    private int number;

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    private Question(QuestionBuilder questionBuilder) {
        this.setId(questionBuilder.id);
        this.text = questionBuilder.text;
        this.type = questionBuilder.type;
        this.maxScore = questionBuilder.maxScore;
    }

    public int getMaxScore() {
        return maxScore;
    }

    public QuestionType getType() {
        return type;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Question question = (Question) o;
        return Objects.equals(getId(), question.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }


    public static class QuestionBuilder {
        private int id;
        private String text;
        private QuestionType type;
        private int maxScore;

        public QuestionBuilder id(int id){
            this.id = id;
            return this;
        }

        public QuestionBuilder text(String text){
            this.text = text;
            return this;
        }

        public QuestionBuilder type(QuestionType type) {
            this.type = type;
            return this;
        }

        public QuestionBuilder maxScore(int maxScore){
            this.maxScore = maxScore;
            return this;
        }

        public Question build(){
            return new Question(this);
        }
    }
}
