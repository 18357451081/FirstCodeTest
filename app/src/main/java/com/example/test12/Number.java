package com.example.test12;

public class Number {
    public Number(String name, int imgeId) {
        this.name = name;
        this.imgeId = imgeId;
    }

    String name;
    int imgeId;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImgeId() {
        return imgeId;
    }

    public void setImgeId(int imgeId) {
        this.imgeId = imgeId;
    }
}
