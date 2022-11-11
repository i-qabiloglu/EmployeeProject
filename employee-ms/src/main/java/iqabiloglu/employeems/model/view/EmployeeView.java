package iqabiloglu.employeems.model.view;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class EmployeeView {

    private Long id;
    private String firstName;
    private String lastName;
    private String gender;
    private LocalDate birthDate;
    private String email;
    private String phoneNumber;
    private String address;
    private Double salary;
    private PositionView position;
}
