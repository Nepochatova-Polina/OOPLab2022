package com.example.application.Entities;

import lombok.Getter;

import javax.persistence.OneToMany;


@Getter
public enum Role {
    CLIENT("client"),
    ADMINISTRATOR("administrator");

    private final String text;

    Role(String text) {
        this.text = text;
    }

    public String toString() {
        return text;
    }
}
