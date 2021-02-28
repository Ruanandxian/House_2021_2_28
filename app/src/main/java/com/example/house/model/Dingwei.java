package com.example.house.model;

public class Dingwei {
    private String nicheng;
    private String dingwei_tag;
    private String classes;
    private String QQ;

    @Override
    public String toString() {
        return "Dingwei{" +
                "nicheng='" + nicheng + '\'' +
                ", dingwei_tag='" + dingwei_tag + '\'' +
                ", classes='" + classes + '\'' +
                ", QQ='" + QQ + '\'' +
                '}';
    }

    public String getNicheng() {
        return nicheng;
    }

    public void setNicheng(String nicheng) {
        this.nicheng = nicheng;
    }

    public String getDingwei_tag() {
        return dingwei_tag;
    }

    public void setDingwei_tag(String dingwei_tag) {
        this.dingwei_tag = dingwei_tag;
    }

    public String getClasses() {
        return classes;
    }

    public void setClasses(String classes) {
        this.classes = classes;
    }

    public String getQQ() {
        return QQ;
    }

    public void setQQ(String QQ) {
        this.QQ = QQ;
    }

    public Dingwei(String nicheng, String dingwei_tag, String classes, String QQ) {
        this.nicheng = nicheng;
        this.dingwei_tag = dingwei_tag;
        this.classes = classes;
        this.QQ = QQ;
    }
}
