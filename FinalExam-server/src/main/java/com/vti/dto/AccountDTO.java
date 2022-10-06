package com.vti.dto;

import com.vti.entity.Department;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.RepresentationModel;

import java.time.LocalDate;

@Data
@NoArgsConstructor
public class AccountDTO extends RepresentationModel<AccountDTO> {
    private int id;
    private String username;
    private String fullName;
    private String role;
    private LocalDate createdDate;
    private DepartmentDTO department;
    private String firstName;
    private String lastName;

    @Data
    @NoArgsConstructor
    public static class DepartmentDTO extends RepresentationModel<DepartmentDTO> {
        private int id;
        private String name;
        private String type;
        private LocalDate createdDate;
    }
}
