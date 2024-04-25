package com.example.quizingapp.components;

public class Question {
        private String question;
        private String[] options;
        private int answerIndex;

        public Question(String question, String[] options, int answerIndex) {
            this.question = question;
            this.options = options;
            this.answerIndex = answerIndex;
        }

        public String getQuestion() {
            return question;
        }

        public String[] getOptions() {
            return options;
        }

        public int getAnswerIndex() {
            return answerIndex;
        }

}
