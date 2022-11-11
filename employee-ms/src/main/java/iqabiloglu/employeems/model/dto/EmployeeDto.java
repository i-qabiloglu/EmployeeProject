package iqabiloglu.employeems.model.dto;

import iqabiloglu.employeems.model.view.PositionView;
import lombok.Data;
import org.springframework.format.annotation.NumberFormat;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;

@Data
public class EmployeeDto {

    @NotNull(message = "First name can't be null")
    @NotBlank(message = "First name must contain at least one character")
    private final String firstName;

    @NotBlank(message = "Last name name must contain at least one character")
    private final String lastName;

    @NotBlank(message = "Gender must not be null and must contain at least one non-whitespace character")
    private final String gender;

    @NotNull(message = "Birth date can't be null")
    private final LocalDate birthDate;

    @Email(regexp = "[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,3}")
    private final String email;

    @NotBlank(message = "Phone number must not be null and must contain at least one non-whitespace character")
    @Pattern(regexp = "^[0-9]{5}-[0-9]{2}-[0-9]{3}-[0-9]{2}-[0-9]{2}$")
    private final String phoneNumber;

    @NotBlank(message = "Address must not be null and must contain at least one non-whitespace character")
    private final String address;

    @NotNull(message = "Salary can't be null")
    @NumberFormat(style = NumberFormat.Style.NUMBER)
    private final Double salary;

    @NotNull(message = "Position must not be null")
    private final PositionView position;
}
