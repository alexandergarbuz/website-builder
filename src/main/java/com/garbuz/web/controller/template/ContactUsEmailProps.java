package com.garbuz.web.controller.template;

public enum ContactUsEmailProps {
    NAME("NAME"),
    PHONE("PHONE"),
    EMAIL("EMAIL"),
    BODY("BODY");

    private final String value;

    ContactUsEmailProps(String placeholder) {
        this.value = placeholder;
    }

    public String getValue() {
        return value;
    }
}
