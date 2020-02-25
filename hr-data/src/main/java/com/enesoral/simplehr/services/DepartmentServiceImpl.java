package com.enesoral.simplehr.services;

import com.enesoral.simplehr.models.Department;
import com.enesoral.simplehr.repositories.DepartmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@RequiredArgsConstructor
@Service
public class DepartmentServiceImpl implements DepartmentService {
    private final DepartmentRepository departmentRepository;

    @Override
    public Department save(Department object) {
        return departmentRepository.save(object);
    }

    @Override
    public Department findById(Long aLong) {
        return departmentRepository.findById(aLong).orElse(null);
    }

    @Override
    public Set<Department> findAll() {
        Set<Department> departments = new HashSet<>();
        departmentRepository.findAll().forEach(departments::add);
        return departments;
    }

    @Override
    public void delete(Department object) {
        departmentRepository.delete(object);
    }

    @Override
    public void deleteById(Long aLong) {
        departmentRepository.deleteById(aLong);
    }
}
