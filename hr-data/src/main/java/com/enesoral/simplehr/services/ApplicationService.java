package com.enesoral.simplehr.services;

import com.enesoral.simplehr.models.Application;
import com.enesoral.simplehr.models.Job;
import com.enesoral.simplehr.models.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ApplicationService extends CrudService<Application, Long> {
    boolean isAlreadyApplied(Job job, User user);
    Page<Application> findUserApplications(User user, Pageable pageable);
}
