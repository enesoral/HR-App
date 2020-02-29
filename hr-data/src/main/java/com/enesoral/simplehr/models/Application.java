package com.enesoral.simplehr.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "applications")
@Entity
public class Application extends BaseEntity {

    @NotNull
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private LocalDate applicationDate;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "job_id")
    private Job job;

    @Size(min = 10, max = 150, message = "Thoughts must be between 10 and 50 characters")
    private String thoughtsOnJob;
}
