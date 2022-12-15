package iqabiloglu.employeems.service.impl;

import iqabiloglu.employeems.dao.entity.DepartmentEntity;
import iqabiloglu.employeems.dao.repository.DepartmentRepository;
import iqabiloglu.employeems.mapper.DepartmentMapper;
import iqabiloglu.employeems.model.dto.DepartmentDto;
import iqabiloglu.employeems.model.exception.AlreadyExistException;
import iqabiloglu.employeems.model.exception.NotFoundException;
import iqabiloglu.employeems.model.view.DepartmentView;
import iqabiloglu.employeems.service.DepartmentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

import static iqabiloglu.employeems.model.constant.ExceptionConstants.*;

@Service
@Slf4j
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentRepository repository;

    public DepartmentServiceImpl(DepartmentRepository repository) {
        this.repository = repository;
    }


    @Override
    public List<DepartmentView> getList() {
        log.info("DepartmentServiceImpl.getList.start");
        var departmentList = repository.findAllDepartments();
        if (departmentList.isEmpty()) {
            throw new NotFoundException(DEPARTMENT_NOT_FOUND_CODE, DEPARTMENTS_NOT_FOUND_MESSAGE);
        }
        return DepartmentMapper.entitiesToViews(departmentList);
    }

    @Override
    public DepartmentView get(Long id) {
        log.info("DepartmentServiceImpl.get.start id: {}", id);
        return DepartmentMapper.entityToView(fetchIfExist(id));
    }

    @Override
    public void create(DepartmentDto dto) {
        log.info("DepartmentServiceImpl.create.start");
        var departmentList = repository.findAllDepartments();
        boolean departmentExist = departmentList.stream().anyMatch(department -> department.getName().equals(dto.getName()));
        if (departmentExist) {
            log.error("DepartmentServiceImpl.create.error with name: {}", dto.getName());
            throw new AlreadyExistException(DEPARTMENT_ALREADY_EXIST_CODE, String.format(DEPARTMENT_ALREADY_EXIST_MESSAGE, dto.getName()));
        }
        var newDepartment = DepartmentMapper.dtoToEntity(dto);
        repository.save(newDepartment);
        log.info("DepartmentServiceImpl.create.end");
    }

    @Override
    public DepartmentView update(Long id, DepartmentDto dto) {
        log.info("DepartmentServiceImpl.update.start id: {}", id);
        var department = fetchIfExist(id);
        department.setName(dto.getName());
        repository.save(department);
        log.info("DepartmentServiceImpl.update.end id: {}", id);
        return DepartmentMapper.entityToView(department);
    }

    @Override
    public void delete(Long id) {
        log.info("DepartmentServiceImpl.delete.start id: {}", id);
        var department = fetchIfExist(id);
        department.setIsDeleted(true);
        repository.save(department);
        log.info("DepartmentServiceImpl.delete.end id: {}", id);
    }

    DepartmentEntity fetchIfExist(Long id) {
        return repository.findByIdAndIsDeletedFalse(id).orElseThrow(() -> {
            log.error("DepartmentServiceImpl.fetchIfExist.error id: {}", id);
            throw new NotFoundException(DEPARTMENT_NOT_FOUND_CODE, String.format(DEPARTMENT_NOT_FOUND_MESSAGE, id));
        });
    }
}
