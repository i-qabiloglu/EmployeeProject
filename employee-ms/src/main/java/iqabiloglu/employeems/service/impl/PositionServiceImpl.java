package iqabiloglu.employeems.service.impl;

import iqabiloglu.employeems.dao.entity.PositionEntity;
import iqabiloglu.employeems.dao.repository.EmployeeRepository;
import iqabiloglu.employeems.dao.repository.PositionRepository;
import iqabiloglu.employeems.mapper.PositionMapper;
import iqabiloglu.employeems.model.dto.PositionDto;
import iqabiloglu.employeems.model.exception.AlreadyExistException;
import iqabiloglu.employeems.model.exception.NotEmptyException;
import iqabiloglu.employeems.model.exception.NotFoundException;
import iqabiloglu.employeems.model.view.PositionView;
import iqabiloglu.employeems.service.PositionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

import static iqabiloglu.employeems.util.constant.ExceptionConstants.*;

@Service
@Slf4j
@RequiredArgsConstructor
public class PositionServiceImpl implements PositionService {

    private final PositionRepository repository;
    private final DepartmentServiceImpl departmentService;
    private final EmployeeRepository employeeRepository;


    @Override
    public List<PositionView> getList() {
        log.info("PositionServiceImpl.getList.start");
        var positionList = repository.findAll();
        if (positionList.isEmpty()) {
            log.error("PositionServiceImpl.getList.error");
            throw new NotFoundException(POSITION_NOT_FOUND_CODE, POSITIONS_NOT_FOUND_MESSAGE);
        }
        return PositionMapper.entitiesToViews(positionList);
    }

    @Override
    public List<PositionView> getListByDepartment(Long departmentId) {
        log.info("PositionServiceImpl.getListByDepartment.start");
        var positionList = repository.findAllByIsDeletedFalseAndDepartment_Id(departmentId);
        if (positionList.isEmpty()) {
            log.error("PositionServiceImpl.getListByDepartment.error departmentId: {}", departmentId);
            throw new NotFoundException(POSITION_NOT_FOUND_CODE, String.format(POSITIONS_BY_DEPARTMET_NOT_FOUND_MESSAGE, departmentId));
        }
        return PositionMapper.entitiesToViews(positionList);
    }

    @Override
    public PositionView get(Long id) {
        return PositionMapper.entityToView(fetchIfExist(id));
    }

    @Override
    public void create(PositionDto dto) {
        log.info("PositionServiceImpl.create.start");
        var department = departmentService.fetchIfExist(dto.getDepartmentId());
        var positionList = repository.findAll();
        boolean positionExist = positionList.stream().anyMatch(position -> position.getName().equals(dto.getName()));
        if (positionExist) {
            log.error("PositionServiceImpl.create.error with name: {}", dto.getName());
            throw new AlreadyExistException(POSITION_ALREADY_EXIST_CODE, String.format(POSITION_ALREADY_EXIST_MESSAGE, dto.getName()));
        }
        var newPosition = PositionMapper.dtoToEntity(dto);
        newPosition.setDepartment(department);
        repository.save(newPosition);
        log.info("PositionServiceImpl.create.end");
    }

    @Override
    public PositionView update(Long id, PositionDto dto) {
        log.info("PositionServiceImpl.update.start with id: {}", id);
        var department = departmentService.fetchIfExist(dto.getDepartmentId());
        var position = fetchIfExist(id);
        position.setName(dto.getName());
        position.setDepartment(department);
        repository.save(position);
        log.info("PositionServiceImpl.update.end with id: {}", id);
        return PositionMapper.entityToView(position);
    }

    @Override
    public void delete(Long id) {
        log.info("PositionServiceImpl.delete.start with id: {}", id);
        var position = fetchIfExist(id);
        var employeeList = employeeRepository.findAllByPosition_IdAndIsDeletedFalse(id);
        if (!employeeList.isEmpty()) {
            log.error("PositionServiceImpl.delete.error with id: {}", id);
            throw new NotEmptyException(POSITION_NOT_EMPTY_CODE, String.format(POSITION_NOT_EMPTY_MESSAGE, id));
        }
        position.setIsDeleted(true);
        repository.save(position);
        log.info("PositionServiceImpl.delete.end with id: {}", id);
    }

    PositionEntity fetchIfExist(Long id) {
        return repository.findByIdAndIsDeletedFalse(id).orElseThrow(() -> {
            log.error("PositionServiceImpl.fetchIfExist.error id: {}", id);
            throw new NotFoundException(POSITION_NOT_FOUND_CODE, String.format(POSITION_NOT_FOUND_MESSAGE));
        });
    }


}
