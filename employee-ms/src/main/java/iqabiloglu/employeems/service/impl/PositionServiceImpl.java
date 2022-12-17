package iqabiloglu.employeems.service.impl;

import iqabiloglu.employeems.dao.entity.PositionEntity;
import iqabiloglu.employeems.dao.repository.PositionRepository;
import iqabiloglu.employeems.mapper.PositionMapper;
import iqabiloglu.employeems.model.dto.PositionDto;
import iqabiloglu.employeems.model.exception.AlreadyExistException;
import iqabiloglu.employeems.model.exception.NotFoundException;
import iqabiloglu.employeems.model.view.PositionView;
import iqabiloglu.employeems.service.PositionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

import static iqabiloglu.employeems.model.constant.ExceptionConstants.*;

@Service
@Slf4j
@RequiredArgsConstructor
public class PositionServiceImpl implements PositionService {

    private final PositionRepository repository;
    private final DepartmentServiceImpl departmentService;


    @Override
    public List<PositionView> getList() {
        return PositionMapper.entitiesToViews(repository.findAll());
    }

    @Override
    public List<PositionView> getListByDepartment(Long departmentId) {
        return PositionMapper.entitiesToViews(repository.findAllByIsDeletedFalseAndDepartment_Id(departmentId));
    }

    @Override
    public PositionView get(Long id) {
        return PositionMapper.entityToView(fetchIfExist(id));
    }

    @Override
    public void create(Long departmentId, PositionDto dto) {
        log.info("PositionServiceImpl.create.start");
        var department = departmentService.fetchIfExist(departmentId);
        var positionList = repository.findAll();
        boolean positionExist = positionList.stream().anyMatch(position -> position.getName().equals(dto.getName()));
        if (positionExist) {
            log.error("PositionServiceImpl.create.error with name: {}", dto.getName());
            throw new AlreadyExistException(POSITION_ALREADY_EXIST_CODE, String.format(POSITION_ALREADY_EXIST_MESSAGE, dto.getName()));
        }
        var newPosition = PositionMapper.dtoToEntity(dto);
        newPosition.setIsDeleted(false);
        newPosition.setDepartment(department);
        repository.save(newPosition);
        log.info("PositionServiceImpl.create.end");
    }

    @Override
    public PositionView update(Long departmentId, Long id, PositionDto dto) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }

    PositionEntity fetchIfExist(Long id) {
        return repository.findByIdAndIsDeletedFalse(id).orElseThrow(() -> {
            log.error("PositionServiceImpl.fetchIfExist.error id: {}", id);
            throw new NotFoundException(POSITION_NOT_FOUND_CODE, String.format(POSITION_NOT_FOUND_MESSAGE));
        });
    }


}
