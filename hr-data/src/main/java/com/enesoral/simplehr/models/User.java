package com.enesoral.simplehr.models;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Table(name = "users")
@Entity
public class User extends Person {
    private String username;
    private String password;

    @Column(columnDefinition = "TINYINT", length = 1)
    private Boolean isManager;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private Set<Application> applications = new HashSet<>();

    @Builder
    public User(String firstName, String lastName, String username, String password, Boolean isManager) {
        super(firstName, lastName);
        this.username = username;
        this.password = password;
        this.isManager = isManager;
    }
}
