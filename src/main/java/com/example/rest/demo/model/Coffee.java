package com.example.rest.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
public class Coffee {
    private final String id;
    private String name;

    public Coffee(String id, String name){
        this.id = id;
        this.name = name;
    }

    public Coffee(String name){
        this(UUID.randomUUID().toString(), name);
    }
}
