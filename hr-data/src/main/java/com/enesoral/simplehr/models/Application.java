package com.enesoral.simplehr.models;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "applications")
@Entity
public class Application extends BaseEntity implements Comparable<Application>{

    @NotNull
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private LocalDateTime applicationDate;

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

    @Override
    public int compareTo(Application app) {
        LocalDateTime d = app.getApplicationDate();

        return d.compareTo(this.applicationDate);
    }
}
