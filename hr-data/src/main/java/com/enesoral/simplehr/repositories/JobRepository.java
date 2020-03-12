package com.enesoral.simplehr.repositories;

import com.enesoral.simplehr.models.Job;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface JobRepository extends JpaRepository<Job, Long> {
     Optional<Job> findByTitle(String title);
     Page<Job> findAllByTitleContaining(String search, Pageable pageable);
}
