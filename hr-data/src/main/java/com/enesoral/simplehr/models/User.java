package com.enesoral.simplehr.models;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Table(name = "users")
@Entity
public class User extends Person {
    @NotNull
    private String username;

    @NotNull
    private String password;

    private String resumeDirectory;

    @Column(columnDefinition = "TINYINT", length = 1)
    private Boolean isManager;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private Set<Application> applications = new HashSet<>();

    @Builder
    public User(String firstName, String lastName, String address, String email, String phone, String username, String password, Boolean isManager) {
        super(firstName, lastName, address, email, phone);
        this.username = username;
        this.password = password;
        this.isManager = isManager;
    }
}
