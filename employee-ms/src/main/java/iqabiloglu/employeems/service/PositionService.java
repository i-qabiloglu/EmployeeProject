package iqabiloglu.employeems.service;

import iqabiloglu.employeems.model.dto.PositionDto;
import iqabiloglu.employeems.model.view.PositionView;

import java.util.List;


public interface PositionService {

    List<PositionView> getList();

    List<PositionView> getListByDepartment(Long departmentId);

    PositionView get(Long departmentId, Long id);

    void create(Long departmentId, PositionDto dto);

    PositionView update(Long departmentId, Long id, PositionDto dto);

    void delete(Long id);
}
