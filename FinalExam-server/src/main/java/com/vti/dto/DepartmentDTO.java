package com.vti.dto;


import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.RepresentationModel;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
public class DepartmentDTO extends RepresentationModel<DepartmentDTO> {
    private int id;
    private String name;
    private String type;
    private LocalDate createdDate;
    private List<AccountDTO> accounts;

    @Data
    @NoArgsConstructor
    public static class AccountDTO extends RepresentationModel<AccountDTO> {
        private int id;
        private String username;
        private String fullName;
        private String role;
        private LocalDate createdDate;
    }
}
