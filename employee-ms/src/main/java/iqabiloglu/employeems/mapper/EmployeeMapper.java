package iqabiloglu.employeems.mapper;

import iqabiloglu.employeems.dao.entity.EmployeeEntity;
import iqabiloglu.employeems.model.dto.EmployeeDto;
import iqabiloglu.employeems.model.view.EmployeeView;
import org.mapstruct.Mapper;
import org.mapstruct.MapperConfig;
import org.mapstruct.Mapping;
import org.mapstruct.MappingInheritanceStrategy;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.Set;

@Mapper(uses = {DepartmentMapper.class, PositionMapper.class})
@MapperConfig(mappingInheritanceStrategy = MappingInheritanceStrategy.AUTO_INHERIT_ALL_FROM_CONFIG)
public abstract class EmployeeMapper {

    public static final EmployeeMapper INSTANCE = Mappers.getMapper(EmployeeMapper.class);

    public abstract EmployeeView entityToView(EmployeeEntity entity);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "isDeleted", constant = "false")
    @Mapping(target = "position", ignore = true)
    public abstract EmployeeEntity dtoToEntity(EmployeeDto dto);

    public abstract List<EmployeeView> entitiesToViews(List<EmployeeEntity> entities);
}
