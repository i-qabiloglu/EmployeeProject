package iqabiloglu.employeems.mapper;

import iqabiloglu.employeems.dao.entity.DepartmentEntity;
import iqabiloglu.employeems.model.dto.DepartmentDto;
import iqabiloglu.employeems.model.view.DepartmentView;

import java.util.Set;
import java.util.stream.Collectors;

public class DepartmentMapper {

    public static DepartmentView entityToView(DepartmentEntity entity) {
        return DepartmentView.builder()
                .id(entity.getId())
                .name(entity.getName())
                .build();
    }

    public static DepartmentEntity dtoToEntity(DepartmentDto dto) {
        return DepartmentEntity.builder()
                .name(dto.getName())
                .isDeleted(false)
                .build();
    }

    public static Set<DepartmentView> entitiesToViews(Set<DepartmentEntity> entities) {
        return entities.stream().map(DepartmentMapper::entityToView).collect(Collectors.toSet());
    }
}
