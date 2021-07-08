package com.example.syzer.recycler_view_items;

public class WordInList {

    private String number;
    private String text;

    public WordInList(String number, String text) {
        this.number = number;
        this.text = text;
    }

    public String getNumber() {
        return number;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setNumber(String number) { this.number = number; }
}
