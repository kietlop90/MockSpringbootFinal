package com.duongam.demo.entities.enums;

public enum EVersion {

    i1("i1"), i2("i2"), i3("i3");
    private final String text;

    private EVersion(final String text) {

        this.text = text;
    }

    @Override
    public String toString() {
        return text;
    }
}
