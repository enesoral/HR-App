package com.enesoral.simplehr.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
@Entity
public class User extends Person {
    private String username;
    private Integer password;

    @Column(columnDefinition = "bit default 0")
    private Boolean isManager;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private Set<Application> applications = new HashSet<>();
}
