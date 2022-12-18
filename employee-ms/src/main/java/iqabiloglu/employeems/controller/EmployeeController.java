package iqabiloglu.employeems.controller;

import iqabiloglu.employeems.model.dto.EmployeeDto;
import iqabiloglu.employeems.model.view.EmployeeView;
import iqabiloglu.employeems.service.EmployeeService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/v1/employees")
public class EmployeeController {

    private final EmployeeService service;

    public EmployeeController(EmployeeService service) {
        this.service = service;
    }

    @GetMapping
    public List<EmployeeView> getList() {
        return service.getList();
    }

    @GetMapping("/{position_id}")
    public List<EmployeeView> getListByPosition(@PathVariable("position_id") Long positionId) {

        return service.getListByPosition(positionId);
    }

    @GetMapping("/{id}")
    public EmployeeView get(@PathVariable Long id) {
        return service.get(id);
    }

    @PostMapping
    public void create(@Valid @RequestBody EmployeeDto dto) {
        service.create(dto);
    }

    @PutMapping("/{id}")
    public EmployeeView update(@PathVariable Long id, @RequestBody EmployeeDto dto) {
        return service.update(id, dto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
