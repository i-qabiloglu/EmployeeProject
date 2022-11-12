package iqabiloglu.employeems.mapper;

import iqabiloglu.employeems.dao.entity.PositionEntity;
import iqabiloglu.employeems.model.dto.PositionDto;
import iqabiloglu.employeems.model.view.PositionView;

import java.util.Set;
import java.util.stream.Collectors;

public class PositionMapper {

    public static PositionView entityToView(PositionEntity entity) {
        return PositionView.builder()
                .id(entity.getId())
                .name(entity.getName())
                .department(DepartmentMapper.entityToView(entity.getDepartment()))
                .build();
    }

    public static PositionEntity dtoToEntity(PositionDto dto) {
        return PositionEntity.builder()
                .name(dto.getName())
                .isDeleted(false)
                .build();
    }

    public static PositionEntity viewToEntity(PositionView view) {
        return PositionEntity.builder()
                .name(view.getName())
                .department(DepartmentMapper.viewToEntity(view.getDepartment()))
                .build();
    }

    public static Set<PositionView> entitiesToViews(Set<PositionEntity> entities) {
        return entities.stream().map(PositionMapper::entityToView).collect(Collectors.toSet());
    }

}
