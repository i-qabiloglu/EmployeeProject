package iqabiloglu.employeems.controller;

import iqabiloglu.employeems.model.dto.PositionDto;
import iqabiloglu.employeems.model.view.PositionView;
import iqabiloglu.employeems.service.PositionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/v1/positions")
@RequiredArgsConstructor
public class PositionController {

    private final PositionService service;

    @GetMapping
    public List<PositionView> getList() {
        return service.getList();
    }

    @GetMapping("/{department_id}")
    public List<PositionView> getListByDepartment(@PathVariable("department_id") Long departmentId) {
        return service.getListByDepartment(departmentId);
    }

    @GetMapping("/{department_id}/{id}")
    public PositionView get(@PathVariable("department_id") Long departmentId, @PathVariable("id") Long id) {
        return service.get(departmentId, id);
    }

    @PostMapping("/{department_id}")
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@PathVariable("department_id") Long departmentId, @Valid @RequestBody PositionDto dto) {
        service.create(departmentId, dto);
    }

    @PutMapping("/{department_id}/{id}/")
    @ResponseStatus(HttpStatus.OK)
    public PositionView update(@PathVariable("department_id") Long departmentId, @PathVariable("id") Long id, @Valid @RequestBody PositionDto dto) {
        return service.update(departmentId, id, dto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

}
