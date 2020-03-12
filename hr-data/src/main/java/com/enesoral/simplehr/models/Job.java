package com.enesoral.simplehr.models;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.time.LocalDateTime;
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
    @NotNull(message = "Name cannot be null")
    @Size(min = 5, max = 50, message = "Title must be between 10 and 50 characters")
    private String title;

    @NotNull(message = "Description cannot be null")
    @Size(min = 10, max = 150, message = "Description must be between 10 and 50 characters")
    private String description;

    @NotNull(message = "Number Of Hire cannot be null")
    @Min(value = 1, message = "Number Of Hire should not be less than 1")
    @Max(value = 100, message = "Number Of Hire should not be greater than 100")
    private Integer numberOfHire;

    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private LocalDate lastApplicationDate;

    @DateTimeFormat(pattern = "dd-MM-yyyy HH:mm")
    private LocalDateTime publishDate;

    @NotNull(message = "Job Type cannot be null")
    @Enumerated
    private JobType jobType;

    @NotNull(message = "Department cannot be null")
    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "job")
    private final Set<Application> applications = new HashSet<>();
}