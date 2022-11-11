package iqabiloglu.employeems.model.view;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PositionView {

    private Long id;
    private String name;
    private DepartmentView department;
}
