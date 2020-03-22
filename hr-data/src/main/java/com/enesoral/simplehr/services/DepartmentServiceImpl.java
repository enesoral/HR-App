package com.enesoral.simplehr.services;

import com.enesoral.simplehr.models.Department;
import com.enesoral.simplehr.repositories.DepartmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import org.springframework.data.domain.Pageable;

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
        if(!departmentRepository.findById(aLong).isPresent()) {
            throw new RuntimeException("Department not found for id value: " + aLong);
        }
        return departmentRepository.findById(aLong).get();
    }

    @Override
    public Page<Department> findAll(Pageable pageable) {
        return departmentRepository.findAll(pageable);
    }

    @Override
    public void delete(Department object) {
        departmentRepository.delete(object);
    }

    @Override
    public void deleteById(Long aLong) {
        if(!departmentRepository.findById(aLong).isPresent()) {
            throw new RuntimeException("Department not found for id value: " + aLong);
        }
        departmentRepository.deleteById(aLong);
    }
}
