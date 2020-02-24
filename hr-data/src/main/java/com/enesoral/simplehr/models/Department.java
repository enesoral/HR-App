package com.enesoral.simplehr.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Table(name = "departments")
@Entity
public class Department extends BaseEntity {

    private String name;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "department")
    private Set<Job> jobs = new HashSet<>();
}
