package com.enesoral.simplehr.models;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "jobs")
@Entity
public class Job extends BaseEntity {

    private String title;
    private String description;
    private Integer numberOfHire;

    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private LocalDate lastApplicationDate;

    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "job")
    private Set<Application> applications = new HashSet<>();
}