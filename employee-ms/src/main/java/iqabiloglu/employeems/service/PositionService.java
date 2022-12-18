package iqabiloglu.employeems.service;

import iqabiloglu.employeems.model.dto.PositionDto;
import iqabiloglu.employeems.model.view.PositionView;

import java.util.List;


public interface PositionService {

    List<PositionView> getList();

    List<PositionView> getListByDepartment(Long departmentId);

    PositionView get(Long id);

    void create(PositionDto dto);

    PositionView update(Long id, PositionDto dto);

    void delete(Long id);
}
