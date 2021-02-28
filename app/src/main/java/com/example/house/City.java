package com.example.house;

import android.app.Application;

public class City extends Application {
    private String city;
    private String xuehao;

    @Override
    public String toString() {
        return "City{" +
                "city='" + city + '\'' +
                ", xuehao='" + xuehao + '\'' +
                '}';
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getXuehao() {
        return xuehao;
    }

    public void setXuehao(String xuehao) {
        this.xuehao = xuehao;
    }
}
