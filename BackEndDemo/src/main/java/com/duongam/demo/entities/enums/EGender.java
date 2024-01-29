package com.duongam.demo.entities.enums;

public enum EGender {
    MALE("MALE"),FEMALE("FEMALE");
    private final String text;

    private EGender(final String text) {

        this.text = text;
    }
    @Override
    public String toString() {
        return text;
    }
}
