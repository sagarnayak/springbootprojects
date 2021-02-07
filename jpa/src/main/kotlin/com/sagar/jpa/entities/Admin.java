package com.sagar.jpa.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.Size;
import java.util.UUID;

@Entity
public class Admin {
    @Id
    @JsonIgnore
    public String id;

    @Size(min = 5)
    public String name;

    public Admin() {
    }

    public Admin(String name) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
    }

    public Admin(String id, String name) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
