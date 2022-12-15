package iqabiloglu.employeems.model.dto;

import iqabiloglu.employeems.model.view.DepartmentView;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class PositionDto {

    @NotBlank(message = "Position name can't be empty and null")
    private String name;

}
