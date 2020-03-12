package com.enesoral.simplehr.services;

import com.enesoral.simplehr.models.Job;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface JobService extends CrudService<Job, Long> {
    Job findByTitle(String title);
    Page<Job> searchJobs(String searchTerm, Pageable pageable);
}
