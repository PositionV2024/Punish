package com.clarence.punish;

public enum Permission {
    Usage("punish.use");

    String name;
    Permission(String name) {
        this.name=name;
    }
    
    public String getName() { return name; }
}
