package com.example.motorzone.models.entities.User;

import com.example.motorzone.models.enums.UserRoleEnum;
import jakarta.persistence.*;

import java.util.Set;

@Entity
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, unique = true)
    private UserRoleEnum name;

    @ManyToMany(mappedBy = "roles")
    private Set<User> users;

    public Role() {}

    public Long getId() {
        return id;
    }

    public Role setId(Long id) {
        this.id = id;
        return this;
    }

    public UserRoleEnum getName() {
        return name;
    }

    public Role setName(UserRoleEnum name) {
        this.name = name;
        return this;
    }

    public Set<User> getUsers() {
        return users;
    }

    public Role setUsers(Set<User> users) {
        this.users = users;
        return this;
    }
}
