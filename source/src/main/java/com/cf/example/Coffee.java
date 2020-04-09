package com.cf.example;

import lombok.Data;



@Data
public class Coffee {
    
    private final String id, name;

    public Coffee(final String id, final String name) {
        this.id = id;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }
}