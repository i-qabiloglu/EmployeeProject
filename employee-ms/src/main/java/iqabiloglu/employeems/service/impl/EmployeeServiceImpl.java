package iqabiloglu.employeems.service.impl;

import iqabiloglu.employeems.dao.entity.EmployeeEntity;
import iqabiloglu.employeems.dao.repository.EmployeeRepository;
import iqabiloglu.employeems.mapper.EmployeeMapper;
import iqabiloglu.employeems.model.dto.EmployeeDto;
import iqabiloglu.employeems.model.exception.AlreadyExistException;
import iqabiloglu.employeems.model.exception.NotFoundException;
import iqabiloglu.employeems.model.view.EmployeeView;
import iqabiloglu.employeems.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

import static iqabiloglu.employeems.model.constant.ExceptionConstants.*;

@Service
@Slf4j
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository repository;
    private final PositionServiceImpl positionService;

    @Override
    public List<EmployeeView> getList() {
        return null;
    }

    @Override
    public List<EmployeeView> getListByPosition(Long positionId) {
        return null;
    }

    @Override
    public EmployeeView get(Long id) {
        return null;
    }

    @Override
    public void create(EmployeeDto dto) {
        log.info("EmployeeServiceImpl.create.start");
        var employeeList = repository.findAll();
        boolean employeeExist = employeeList.stream().anyMatch(employee -> employee.getEmail().equals(dto.getEmail()));
        if (employeeExist) {
            log.error("EmployeeServiceImpl.create.error with email: {}", dto.getEmail());
            throw new AlreadyExistException(EMPLOYEE_ALREADY_EXIST_CODE, String.format(EMPLOYEE_ALREADY_EXIST_MESSAGE, dto.getEmail()));
        }
        var newEmployee = EmployeeMapper.INSTANCE.dtoToEntity(dto);
        var position = positionService.fetchIfExist(dto.getPositionId());
        newEmployee.setPosition(position);
        repository.save(newEmployee);
        log.info("EmployeeServiceImpl.create.end");
    }

    @Override
    public EmployeeView update(Long id, EmployeeDto dto) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }

    public EmployeeEntity fetchIfExist(Long id) {
        return repository.findByIdAndIsDeletedFalse(id).orElseThrow(() -> {
            log.error("EmployeeServiceImpl.fetchIfExist.error with id: {}", id);
            throw new NotFoundException(EMPLOYEE_NOT_FOUND_CODE, String.format(EMPLOYEE_NOT_FOUND_MESSAGE, id));
        });
    }
}