package com.vti.service;


import com.vti.entity.Department;

import com.vti.form.DepartmentCreateForm;
import com.vti.form.DepartmentFilterForm;
import com.vti.form.DepartmentUpdateForm;
import com.vti.repository.IDepartmentRepository;
import com.vti.specification.DepartmentSpecification;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentService implements IDepartmentService {
    @Autowired
    private IDepartmentRepository repository;

    @Autowired
    private ModelMapper mapper;

    @Override
    public Page<Department> findAll(Pageable pageable, DepartmentFilterForm form) {
        return repository.findAll(DepartmentSpecification.buildWhere(form), pageable);
    }

    @Override
    public Department findById(int id){
        return repository.findById(id).orElse(null);
    }

    @Override
    public void create(DepartmentCreateForm form){
        repository.save(mapper.map(form, Department.class));
    }

    @Override
    public void update(DepartmentUpdateForm form){
        repository.save(mapper.map(form, Department.class));
    }

    @Override
    public void deleteById(int id) {
        repository.deleteById(id);
    }

    @Override
    public void deleteAll(List<Integer> ids) {
        repository.deleteAllById(ids);
    }
}
