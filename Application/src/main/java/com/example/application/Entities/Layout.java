package com.example.application.Entities;

import lombok.Getter;

@Getter
public enum Layout {
    STANDARD("standard"),
    DELUXE("deluxe"),
    JOINT("joint"),
    SUITE("suite");


    private final String text;

    Layout(String text) {
        this.text = text;
    }

    public String toString() {
        return text;
    }
}
