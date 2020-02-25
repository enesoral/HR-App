package com.enesoral.simplehr.repositories;

import com.enesoral.simplehr.models.Application;
import org.springframework.data.repository.CrudRepository;

public interface ApplicationRepository extends CrudRepository<Application, Long> {
}
