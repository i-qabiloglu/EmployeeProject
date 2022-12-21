package iqabiloglu.employeems.model.view;

import com.fasterxml.jackson.annotation.JsonFormat;
import iqabiloglu.employeems.util.constant.Constants;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.springframework.format.annotation.NumberFormat;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;

@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class EmployeeView {

     Long id;

    @NotBlank
    String firstName;

    @NotBlank
     String lastName;

    @NotBlank
     String gender;

    @NotNull
    @JsonFormat(pattern = Constants.DATE_FORMAT)
     LocalDate birthDate;

    @Email(regexp ="[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,3}" )
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
    PositionView position;
}
