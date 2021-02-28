package com.example.house.model;

public class Student {
    private String xuehao;
    private String nicheng;
    private String room;
    private String question;
    private String Squestion;
    private String QQ;

    @Override
    public String toString() {
        return "Student{" +
                "xuehao='" + xuehao + '\'' +
                ", nicheng='" + nicheng + '\'' +
                ", room='" + room + '\'' +
                ", question='" + question + '\'' +
                ", Squestion='" + Squestion + '\'' +
                ", QQ='" + QQ + '\'' +
                '}';
    }

    public String getXuehao() {
        return xuehao;
    }

    public void setXuehao(String xuehao) {
        this.xuehao = xuehao;
    }

    public String getNicheng() {
        return nicheng;
    }

    public void setNicheng(String nicheng) {
        this.nicheng = nicheng;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getSquestion() {
        return Squestion;
    }

    public void setSquestion(String squestion) {
        Squestion = squestion;
    }

    public String getQQ() {
        return QQ;
    }

    public void setQQ(String QQ) {
        this.QQ = QQ;
    }

    public Student(String xuehao, String nicheng, String room, String question, String squestion, String QQ) {
        this.xuehao = xuehao;
        this.nicheng = nicheng;
        this.room = room;
        this.question = question;
        Squestion = squestion;
        this.QQ = QQ;
    }
}
