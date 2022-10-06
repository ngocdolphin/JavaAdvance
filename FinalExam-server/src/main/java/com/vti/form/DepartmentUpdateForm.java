package com.vti.form;

import com.vti.validation.DepartmentExistsById;
import com.vti.validation.DepartmentNotExistsByName;
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
public class DepartmentUpdateForm {
    @NotNull(message = "{DepartmentForm.id.NotNull}")
    @DepartmentExistsById
    private Integer id;

    @NotBlank(message = "{DepartmentForm.name.NotBlank}")
    @Length(max = 50, message = "{DepartmentForm.name.Length}")
    private String name;

    @NotNull(message = "{DepartmentForm.type.NotNull}")
    @Pattern(
            regexp = "DEVELOPER|TESTER|SCRUM_MASTER|PROJECT_MANAGER",
            message = "{DepartmentForm.type.Pattern}"
    )
    private String type;
}
