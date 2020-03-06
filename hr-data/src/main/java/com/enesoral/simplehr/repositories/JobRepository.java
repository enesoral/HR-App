package com.enesoral.simplehr.repositories;

import com.enesoral.simplehr.models.Job;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;
import java.util.Set;

public interface JobRepository extends CrudRepository<Job, Long> {
     Optional<Job> findByTitle(String title);
     Set<Job> findAllByTitleContaining(String search);
}
