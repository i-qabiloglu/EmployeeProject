package iqabiloglu.employeems.model.view;

import com.fasterxml.jackson.annotation.JsonFormat;
import iqabiloglu.employeems.util.constant.Constants;
import lombok.Builder;
import lombok.Data;
import org.springframework.format.annotation.NumberFormat;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;
import java.util.UUID;

@Data
@Builder
public class EmployeeView {

    private Long id;

    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;

    @NotBlank
    private String gender;

    @NotNull
    @JsonFormat(pattern = Constants.DATE_FORMAT)
    private LocalDate birthDate;

    @Email(regexp ="[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,3}" )
    private String email;

    @NotBlank
    @Pattern(regexp = "^[0-9]{5}-[0-9]{2}-[0-9]{3}-[0-9]{2}-[0-9]{2}$")
    private String phoneNumber;

    @NotBlank
    private String address;

    @NotNull
    @NumberFormat
    private Double salary;

    @NotNull
    private PositionView position;
}
