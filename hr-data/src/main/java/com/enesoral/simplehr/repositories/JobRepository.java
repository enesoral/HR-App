package com.enesoral.simplehr.repositories;

import com.enesoral.simplehr.models.Job;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface JobRepository extends CrudRepository<Job, Long> {
     Optional<Job> findByTitle(String title);
}
