package com.enesoral.simplehr.services;

import com.enesoral.simplehr.models.Job;

import java.security.acl.Owner;
import java.util.Set;

public interface JobService extends CrudService<Job, Long> {
    Job findByTitle(String title);
    Set<Job> searchJobs(String search);
}
