package com.example.store.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "categories")
@Getter
@Setter
@Builder
@ToString
public class Category {

    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Byte id;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "category")
    private Set<Product> products = new HashSet<>();

}
