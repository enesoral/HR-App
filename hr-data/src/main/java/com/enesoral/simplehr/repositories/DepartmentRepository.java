package com.enesoral.simplehr.repositories;

import com.enesoral.simplehr.models.Department;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface DepartmentRepository extends CrudRepository<Department, Long>, PagingAndSortingRepository<Department, Long> {
}
