package com.clarence.punish;

public enum BanType {
    Temporary("Temporary"),
    Permanent("Permanent");
    private String name;

    BanType(String name){
        this.name = name;
    }
}
