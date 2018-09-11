package com.example.abhin_000.bountyhunt;

public class QuestionFormat {
    private int score;
    private String name;
    private String question;
    private String QID;
    private String answer;
    private String askedID;
    private String answeredID;

    public QuestionFormat() {
    }

    public QuestionFormat(int score, String name, String question) {
        this.score = score;
        this.name = name;
        this.question = question;
    }

    public QuestionFormat(String QID, String question, String askedID){
           this.QID = QID;
           this.question = question;
           this.answer = "Not yet answered";
           this.askedID = askedID;
           this.answeredID = "Not yet answered";
    }

    public int getScore() {
        return score;
    }

    public String getName() {
        return name;
    }

    public String getQuestion() {
        return question;
    }

    public String getQID() {
        return QID;
    }

    public void setQID(String QID) {
        this.QID = QID;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getAskedID() {
        return askedID;
    }

    public void setAskedID(String askedID) {
        this.askedID = askedID;
    }

    public String getAnsweredID() {
        return answeredID;
    }

    public void setAnsweredID(String answeredID) {
        this.answeredID = answeredID;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setQuestion(String question) {
        this.question = question;
    }
}
