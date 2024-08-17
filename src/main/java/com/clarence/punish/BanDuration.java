package com.clarence.punish;

public enum BanDuration {
    MINUTES("MINUTES"),
    HOURS("HOURS");

    private String name;

    BanDuration(String name) {
        this.name = name;
    }
}
