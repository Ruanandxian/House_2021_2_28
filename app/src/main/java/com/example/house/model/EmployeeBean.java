package com.example.house.model;

public class EmployeeBean {
    private String xuehao;
    private String userName;
    private String nameInfo;
    private String QQ;
    private String employeeRole;


    @Override
    public String toString() {
        return "EmployeeBean{" +
                "xuehao='" + xuehao + '\'' +
                ", userName='" + userName + '\'' +
                ", nameInfo='" + nameInfo + '\'' +
                ", QQ='" + QQ + '\'' +
                ", employeeRole='" + employeeRole + '\'' +
                '}';
    }

    public String getXuehao() {
        return xuehao;
    }

    public void setXuehao(String xuehao) {
        this.xuehao = xuehao;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getNameInfo() {
        return nameInfo;
    }

    public void setNameInfo(String nameInfo) {
        this.nameInfo = nameInfo;
    }

    public String getQQ() {
        return QQ;
    }

    public void setQQ(String QQ) {
        this.QQ = QQ;
    }

    public String getEmployeeRole() {
        return employeeRole;
    }

    public void setEmployeeRole(String employeeRole) {
        this.employeeRole = employeeRole;
    }

    public EmployeeBean(String xuehao, String userName, String nameInfo, String QQ, String employeeRole) {
        this.xuehao = xuehao;
        this.userName = userName;
        this.nameInfo = nameInfo;
        this.QQ = QQ;
        this.employeeRole = employeeRole;
    }
}
