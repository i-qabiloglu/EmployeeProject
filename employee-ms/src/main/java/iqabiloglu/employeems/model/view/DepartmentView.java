package iqabiloglu.employeems.model.view;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DepartmentView {

    private Long id;
    private String name;
}
