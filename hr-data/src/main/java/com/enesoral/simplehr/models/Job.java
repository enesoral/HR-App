package com.enesoral.simplehr.models;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
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
public class Job extends BaseEntity implements Comparable<Job> {

    private String title;
    private String description;
    private Integer numberOfHire;

    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private LocalDate lastApplicationDate;

    @DateTimeFormat(pattern = "dd-MM-yyyy HH:mm")
    private LocalDateTime publishDate;

    @Enumerated
    private JobType jobType;

    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "job")
    private Set<Application> applications = new HashSet<>();

    @Override
    public int compareTo(Job job) {
        LocalDateTime d = job.getPublishDate();

        int cmp = (d.getYear() - publishDate.getYear());
        if (cmp == 0) {
            cmp = (d.getMonthValue() - publishDate.getMonthValue());
            if (cmp == 0) {
                cmp = (d.getDayOfYear() - publishDate.getDayOfYear());
                if(cmp == 0) {
                    cmp = (d.getHour() - publishDate.getHour());
                    if (cmp == 0) {
                        cmp = (d.getMinute()- publishDate.getMinute());
                    }
                }
            }
        }
        return cmp;
    }
}