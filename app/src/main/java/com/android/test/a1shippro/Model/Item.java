package com.android.test.a1shippro.Model;

/**
 * Created by Zanty on 09/06/2016.
 */
public class Item {
    private String title;
    private String day;
    private String time;
    private String congviec;
    private String diadiem;
    private boolean check;

    public Item() {
    }

    public Item(String title, String day, String time, String congviec, String diadiem, boolean check) {
        this.title = "ĐƠN HÀNG "+title;
        this.day = day;
        this.time = time;
        this.congviec = congviec;
        this.diadiem = diadiem;
        this.check = check;
    }

    public boolean isCheck() {
        return check;
    }

    public void setCheck(boolean check) {
        this.check = check;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = "ĐƠN HÀNG "+title;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getCongviec() {
        return congviec;
    }

    public void setCongviec(String congviec) {
        this.congviec = congviec;
    }

    public String getDiadiem() {
        return diadiem;
    }

    public void setDiadiem(String diadiem) {
        this.diadiem = diadiem;
    }
}
