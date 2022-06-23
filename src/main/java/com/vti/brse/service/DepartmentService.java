package com.vti.brse.service;

import com.vti.brse.entity.DepartmentEntity;
import com.vti.brse.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DepartmentService {

    @Autowired
    DepartmentRepository departmentRepository;

    public List<DepartmentEntity> getAllDepartments() {
        return departmentRepository.findAll();
    }

    public Optional<DepartmentEntity> getDepartmentById(Integer departmentId) {
        return departmentRepository.findById(departmentId);
    }

    public void deleteDepartmentById(Integer departmentId) {
        departmentRepository.deleteById(departmentId);
    }

    public DepartmentEntity createDepartment(DepartmentEntity department) {
        department.setId(null);
        return departmentRepository.save(department);
    }
}
