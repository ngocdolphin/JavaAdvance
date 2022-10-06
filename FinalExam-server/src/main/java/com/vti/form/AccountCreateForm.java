package com.vti.form;

import com.vti.validation.AccountNotExistsByUsername;
import com.vti.validation.DepartmentExistsById;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Getter
@Setter
@NoArgsConstructor
public class AccountCreateForm {
    @NotBlank(message = "{AccountForm.username.NotBlank}")
    @Length(max = 50, message = "{AccountForm.username.Length}")
    @AccountNotExistsByUsername
    private String username;

    @NotBlank(message = "{AccountForm.password.NotBlank}")
    @Length(max = 32, message = "{AccountForm.password.Length}")
    private String password;

    @NotBlank(message = "{AccountForm.firstname.NotBlank}")
    @Length(max = 50, message = "{AccountForm.firstname.Length}")
    private String firstName;

    @NotBlank(message = "{AccountForm.lastname.NotBlank}")
    @Length(max = 50, message = "{AccountForm.lastname.Length}")
    private String lastName;

    @NotNull(message = "{AccountForm.role.NotNull}")
    @Pattern(
            regexp = "ADMIN|MANAGER|EMPLOYEE",
            message = "{AccountForm.role.Pattern}"
    )
    private String role ;

    @NotNull(message = "{AccountForm.departmentId.NotNull}")
    @DepartmentExistsById
    private Integer departmentId;
}
