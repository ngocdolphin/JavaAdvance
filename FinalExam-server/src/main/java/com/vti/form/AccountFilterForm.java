package com.vti.form;

import com.vti.entity.Role;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class AccountFilterForm {
    private String search;
    private Integer departmentId;
    private Integer maxId;
    private Integer minId;
    private Role role;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate minCreatedDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate maxCreatedDate;
    private Integer minYear;
    private Integer maxYear;
}
