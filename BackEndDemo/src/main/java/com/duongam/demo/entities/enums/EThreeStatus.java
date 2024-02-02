package com.duongam.demo.entities.enums;

public enum EThreeStatus {

    active("active"), Inactive("Inactive"), draft("draft");
    private final String text;

    EThreeStatus(final String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return super.toString();
    }


}
