package com.vti.brse.controller;

import com.vti.brse.entity.DepartmentEntity;
import com.vti.brse.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/departments")
public class DepartmentController {

    @Autowired
    DepartmentService departmentService;

    @GetMapping
    public List<DepartmentEntity> getAllDepartments() {
        //0 .. n
        return departmentService.getAllDepartments();
    }

    @GetMapping("/{departmentId}")
    public Optional<DepartmentEntity> getDepartmentById(@PathVariable Integer departmentId) {
        //DepartmentEntity 1 1
        //Optional<DepartmentEntity> 0 1
        return departmentService.getDepartmentById(departmentId);
    }

    @DeleteMapping("/{departmentId}")
    public void deleteDepartmentById(@PathVariable Integer departmentId) {
        departmentService.deleteDepartmentById(departmentId);
    }

    @PostMapping
    public DepartmentEntity createDepartment(@RequestBody DepartmentEntity department) {
        return departmentService.createDepartment(department);
    }

}
