package com.example.pantry.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;
    private String email;
    @ManyToMany
    @JoinTable(
            name = "user_items",  // This is the join table that links users and items
            joinColumns = @JoinColumn(name = "user_id"),  // The column in the join table that refers to the User entity
            inverseJoinColumns = @JoinColumn(name = "item_id")  // The column in the join table that refers to the Item entity
    )
    private List<Item> items;
}
