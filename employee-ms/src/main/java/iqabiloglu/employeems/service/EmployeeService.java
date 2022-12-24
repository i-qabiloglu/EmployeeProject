package iqabiloglu.employeems.service;

import iqabiloglu.employeems.model.criteria.EmployeeCriteria;
import iqabiloglu.employeems.model.criteria.PageCriteria;
import iqabiloglu.employeems.model.dto.EmployeeDto;
import iqabiloglu.employeems.model.dto.PageableEmployeeDto;
import iqabiloglu.employeems.model.view.EmployeeView;

import java.util.List;

public interface EmployeeService {

    PageableEmployeeDto getList(PageCriteria pageCriteria, EmployeeCriteria criteria);

    List<EmployeeView> getListByPosition(Long positionId);

    EmployeeView get(Long id);

    void create(EmployeeDto dto);

    EmployeeView update(Long id, EmployeeDto dto);

    void delete(Long id);

}
