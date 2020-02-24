package com.enesoral.simplehr.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "jobs")
@Entity
public class Job extends BaseEntity {

    private String title;
    private String description;
    private Integer numberOfHire;

    @DateTimeFormat(pattern = "dd-MM-yyyy hh:mm")
    private Date lastApplicationDate;

    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "job")
    private Set<Application> applications = new HashSet<>();
}