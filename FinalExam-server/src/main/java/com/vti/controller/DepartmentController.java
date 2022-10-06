package com.vti.controller;

import com.vti.dto.DepartmentDTO;
import com.vti.entity.Department;
import com.vti.form.DepartmentCreateForm;
import com.vti.form.DepartmentFilterForm;
import com.vti.form.DepartmentUpdateForm;
import com.vti.service.IDepartmentService;
import com.vti.validation.DepartmentExistsById;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import java.util.List;
import java.util.Locale;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Validated
@RestController
@RequestMapping("/api/v1/departments")
public class DepartmentController {
    @Autowired
    private IDepartmentService service;

    @Autowired
    private ModelMapper mapper;

//    @Autowired
//    private MessageSource messageSource;

    @GetMapping
    public Page<DepartmentDTO> findAll(Pageable pageable, DepartmentFilterForm form) {
        Page<Department> page = service.findAll(pageable, form);
        List<DepartmentDTO> dtos = mapper.map(
                page.getContent(),
                new TypeToken<List<DepartmentDTO>>() {
                }.getType()
        );

        for (DepartmentDTO departmentDTO : dtos) {
            List<DepartmentDTO.AccountDTO> accountDTOS = departmentDTO.getAccounts();
            for (DepartmentDTO.AccountDTO accountDTO : accountDTOS) {
                accountDTO.add(linkTo(methodOn(AccountController.class).findById(accountDTO.getId())).withSelfRel());
            }
            departmentDTO.add(linkTo(methodOn(DepartmentController.class).findById(departmentDTO.getId())).withSelfRel());
        }
        return new PageImpl<>(dtos, pageable, page.getTotalElements());
    }

    @GetMapping("/{id}")
    public DepartmentDTO findById(@PathVariable("id") @DepartmentExistsById int id) {
        return mapper.map(service.findById(id), DepartmentDTO.class)
                .add(linkTo(methodOn(DepartmentController.class).findById(id)).withSelfRel());
    }

    @PostMapping
    public void create(@Valid @RequestBody DepartmentCreateForm form) {
        service.create(form);
    }

    @PutMapping("/{id}")
    public void update(@PathVariable("id") @DepartmentExistsById int id, @RequestBody @Valid DepartmentUpdateForm form) {
        form.setId(id);
        service.update(form);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable("id") @DepartmentExistsById int id) {
        service.deleteById(id);
    }

    @DeleteMapping
    public void deleteAll(@RequestBody List<@DepartmentExistsById Integer> ids){
        service.deleteAll(ids);
    }

//    @GetMapping("/messages/vi")
//    public String testMessagesVi(@RequestParam(value = "key") String key){
//        return messageSource.getMessage(
//                key,
//                null,
//                "Default message",
//                new Locale("vi", "VN"));
//    }
//
//    @GetMapping("/messages/en")
//    public String testMessagesEn(@RequestParam(value = "key") String key){
//        return messageSource.getMessage(
//                key,
//                null,
//                "Default message",
//                Locale.US);
//    }
//
//    @GetMapping("/exception")
//    public void testException() {
//        throw new EntityNotFoundException("... Exception information");
//    }

}
