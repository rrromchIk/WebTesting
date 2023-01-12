package com.epam.testing.model.entity;

/**
 * Test entity class
 *
 * @author rom4ik
 */

public class Test extends Entity {
    private String name;
    private String subject;
    private TestDifficulty testDifficulty;
    private Integer numberOfQuestions;
    private Integer duration;
    private TestStatus status;

    private Test(TestBuilder builder) {
        this.name = builder.name;
        this.subject = builder.subject;
        this.testDifficulty = builder.testDifficulty;
        this.numberOfQuestions = builder.numberOfQuestions;
        this.duration = builder.duration;
    }

    public TestStatus getStatus() {
        return status;
    }

    public void setStatus(TestStatus status) {
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public TestDifficulty getDifficulty() {
        return testDifficulty;
    }

    public void setDifficulty(TestDifficulty testDifficulty) {
        this.testDifficulty = testDifficulty;
    }

    public Integer getNumberOfQuestions() {
        return numberOfQuestions;
    }

    public void setNumberOfQuestions(Integer numberOfQuestions) {
        this.numberOfQuestions = numberOfQuestions;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }


    @Override
    public String toString() {
        return "Test {" +
                "name = " + name +
                ", subject = " + subject +
                ", difficulty = " + testDifficulty +
                ", numberOfQuestions = " + numberOfQuestions +
                ", duration = " + duration + "}";
    }

    /**
     * Builder.
     */
    public static class TestBuilder {
        private String name;
        private String subject;
        private TestDifficulty testDifficulty;
        private Integer numberOfQuestions;
        private Integer duration;

        public TestBuilder name(String name) {
            this.name = name;
            return this;
        }

        public TestBuilder subject(String subject) {
            this.subject = subject;
            return this;
        }

        public TestBuilder difficulty(TestDifficulty testDifficulty) {
            this.testDifficulty = testDifficulty;
            return this;
        }

        public TestBuilder numberOfQuestions(Integer numberOfQuestions) {
            this.numberOfQuestions = numberOfQuestions;
            return this;
        }

        public TestBuilder duration(Integer duration) {
            this.duration = duration;
            return this;
        }

        public Test build() {
            return new Test(this);
        }
    }
}



