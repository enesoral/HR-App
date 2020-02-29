package com.enesoral.simplehr.services;

import com.enesoral.simplehr.models.Application;
import com.enesoral.simplehr.models.Job;
import com.enesoral.simplehr.models.User;

public interface ApplicationService extends CrudService<Application, Long> {
    boolean isAlreadyApplied(Job job, User user);
}
