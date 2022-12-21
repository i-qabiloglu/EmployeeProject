package iqabiloglu.employeems.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import iqabiloglu.employeems.util.constant.Constants;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.format.annotation.NumberFormat;

import javax.validation.constraints.*;
import java.time.LocalDate;
import java.time.Period;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class EmployeeDto {

    @NotBlank
    String firstName;

    @NotBlank
    String lastName;

    @NotBlank
    String gender;

    @NotNull
    @JsonFormat(pattern = Constants.DATE_FORMAT)
    LocalDate birthDate;

    @Email(regexp = "[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,3}")
    String email;

    @NotBlank
    @Pattern(regexp = "^[0-9]{5}-[0-9]{2}-[0-9]{3}-[0-9]{2}-[0-9]{2}$")
    String phoneNumber;

    @NotBlank
    String address;

    @NotNull
    @NumberFormat
    Double salary;

    @NotNull
    @Min(0L)
    Long positionId;

    @AssertTrue(message = "Employee must be over 17 years old")
    boolean isAgeValid() {
        boolean isValid = Period.between(birthDate, LocalDate.now()).getYears() >= 18;
        return ObjectUtils.isNotEmpty(birthDate) && isValid;
    }


}
