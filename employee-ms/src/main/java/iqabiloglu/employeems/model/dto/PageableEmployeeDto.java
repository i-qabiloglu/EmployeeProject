package iqabiloglu.employeems.model.dto;


import iqabiloglu.employeems.model.view.EmployeeView;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class PageableEmployeeDto {

    List<EmployeeView> employees;
    Long totalElements;
    Integer totalPages;
    Boolean hasNextPage;

}
