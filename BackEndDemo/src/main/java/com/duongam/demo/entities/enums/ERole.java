package com.duongam.demo.entities.enums;

public enum ERole {
    CUSTOMER("CUSTOMER"), ADMIN("ADMIN");
    private final String text;

    private ERole(final String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return text;
    }


}
