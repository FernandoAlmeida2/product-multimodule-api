package com.example.models;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "TB_CATEGORIES")
public class CategoryModel {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID idCategory;
    private String name;

    public UUID getIdCategory() {
        return idCategory;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
