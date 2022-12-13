package iqabiloglu.employeems.service.impl;

import iqabiloglu.employeems.model.dto.PositionDto;
import iqabiloglu.employeems.model.view.PositionView;
import iqabiloglu.employeems.service.PositionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class PositionServiceImpl implements PositionService {

    @Override
    public List<PositionView> getList() {
        return null;
    }

    @Override
    public List<PositionView> getListByDepartment(Long departmentId) {
        return null;
    }

    @Override
    public PositionView get(Long departmentId, Long id) {
        return null;
    }

    @Override
    public void create(Long departmentId, PositionDto dto) {

    }

    @Override
    public PositionView update(Long departmentId, Long id, PositionDto dto) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }
}
