package com.enesoral.simplehr.repositories;

import com.enesoral.simplehr.models.Department;
import org.springframework.data.repository.CrudRepository;

public interface DepartmentRepository extends CrudRepository<Department, Long> {
}
