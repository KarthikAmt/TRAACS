package com.verteil.traacsbackofficeconnector.dto.mapper;

public class DateOfIssue {
    private int year;
    private int month;
    private int day;

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    @Override
    public String toString() {
        return "DateOfIssue{" +
                "year=" + year +
                ", month=" + month +
                ", day=" + day +
                '}';
    }
}
