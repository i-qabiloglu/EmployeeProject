package iqabiloglu.employeems.controller;

import iqabiloglu.employeems.model.dto.DepartmentDto;
import iqabiloglu.employeems.model.view.DepartmentView;
import iqabiloglu.employeems.service.impl.DepartmentServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/v1/departments")
@RequiredArgsConstructor
public class DepartmentController {

    private final DepartmentServiceImpl service;

    @GetMapping
    public List<DepartmentView> getList() {
        return service.getList();
    }

    @GetMapping("/{id}")
    public DepartmentView get(@PathVariable Long id) {
        return service.get(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@Valid @RequestBody DepartmentDto dto) {
        service.create(dto);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public DepartmentView update(@PathVariable Long id, @Valid @RequestBody DepartmentDto dto) {
        return service.update(id, dto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

}
