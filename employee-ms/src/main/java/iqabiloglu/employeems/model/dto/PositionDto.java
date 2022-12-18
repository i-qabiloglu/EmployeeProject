package iqabiloglu.employeems.model.dto;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class PositionDto {

    @NotBlank(message = "Position name can't be empty and null")
    private String name;

    @Min(0L)
    @NotNull
    private Long departmentId;

}
