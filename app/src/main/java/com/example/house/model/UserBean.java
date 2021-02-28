package com.example.house.model;

public class UserBean {
    private int id;
    private String su;
    private String xuehao;
    private String name;
    private String sex;
    private String nicheng;
    private String password;
    private String room;
    private String cla;
    private int dingwei_tag;
    private String dingwei;
    private String question;
    private String Squestion;
    private String QQ;
    private String admin;


    public UserBean() {
    }


    @Override
    public String toString() {
        return "UserBean{" +
                "id=" + id +
                ", su='" + su + '\'' +
                ", xuehao='" + xuehao + '\'' +
                ", name='" + name + '\'' +
                ", sex='" + sex + '\'' +
                ", nicheng='" + nicheng + '\'' +
                ", password='" + password + '\'' +
                ", room='" + room + '\'' +
                ", cla='" + cla + '\'' +
                ", dingwei_tag=" + dingwei_tag +
                ", dingwei='" + dingwei + '\'' +
                ", question='" + question + '\'' +
                ", Squestion='" + Squestion + '\'' +
                ", QQ='" + QQ + '\'' +
                ", admin='" + admin + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSu() {
        return su;
    }

    public void setSu(String su) {
        this.su = su;
    }

    public String getXuehao() {
        return xuehao;
    }

    public void setXuehao(String xuehao) {
        this.xuehao = xuehao;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getNicheng() {
        return nicheng;
    }

    public void setNicheng(String nicheng) {
        this.nicheng = nicheng;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public String getCla() {
        return cla;
    }

    public void setCla(String cla) {
        this.cla = cla;
    }

    public int getDingwei_tag() {
        return dingwei_tag;
    }

    public void setDingwei_tag(int dingwei_tag) {
        this.dingwei_tag = dingwei_tag;
    }

    public String getDingwei() {
        return dingwei;
    }

    public void setDingwei(String dingwei) {
        this.dingwei = dingwei;
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

    public String getAdmin() {
        return admin;
    }

    public void setAdmin(String admin) {
        this.admin = admin;
    }

    public UserBean(int id, String su, String xuehao, String name, String sex, String nicheng, String password, String room, String cla, int dingwei_tag, String dingwei, String question, String squestion, String QQ, String admin) {
        this.id = id;
        this.su = su;
        this.xuehao = xuehao;
        this.name = name;
        this.sex = sex;
        this.nicheng = nicheng;
        this.password = password;
        this.room = room;
        this.cla = cla;
        this.dingwei_tag = dingwei_tag;
        this.dingwei = dingwei;
        this.question = question;
        Squestion = squestion;
        this.QQ = QQ;
        this.admin = admin;
    }
}
