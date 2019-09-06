package com.gmail.kirill.backend.entity;

import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;

abstract class Entity {
    @Id
    @GeneratedValue
    private Long id;

    public Long getId() {
        return id;
    }
}
