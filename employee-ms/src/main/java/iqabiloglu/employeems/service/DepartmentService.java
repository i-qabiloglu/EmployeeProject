package iqabiloglu.employeems.service;

import iqabiloglu.employeems.model.dto.DepartmentDto;
import iqabiloglu.employeems.model.view.DepartmentView;

import java.util.List;

public interface DepartmentService {

    List<DepartmentView> getList();

    DepartmentView get(Long id);

    void create(DepartmentDto dto);

    DepartmentView update(Long id, DepartmentDto dto);

    void delete(Long id);
}
