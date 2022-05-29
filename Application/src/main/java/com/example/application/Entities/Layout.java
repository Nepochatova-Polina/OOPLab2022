package com.example.application.Entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
