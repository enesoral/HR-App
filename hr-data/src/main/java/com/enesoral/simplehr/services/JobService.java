package com.enesoral.simplehr.services;

import com.enesoral.simplehr.models.Job;

import java.security.acl.Owner;

public interface JobService extends CrudService<Job, Long> {
    Job findByTitle(String title);
}
