package iqabiloglu.employeems.mapper

import io.github.benas.randombeans.EnhancedRandomBuilder
import iqabiloglu.employeems.dao.entity.DepartmentEntity
import iqabiloglu.employeems.model.dto.DepartmentDto
import spock.lang.Specification

class DepartmentMapperTest extends Specification {

    private random = EnhancedRandomBuilder.aNewEnhancedRandom()


    def "entityToView"() {
        given:
        def entity = random.nextObject(DepartmentEntity)

        when:
        def result = DepartmentMapper.entityToView(entity)

        then:
        result.id == entity.id
        result.name == entity.name
    }

    def "dtoToEntity"() {
        given:
        def dto = random.nextObject(DepartmentDto)

        when:
        def result = DepartmentMapper.dtoToEntity(dto)

        then:
        result.name == dto.name
        result.isDeleted == false
    }

    def "test entitiesToViews"() {
        given:
        def entity = random.nextObject(DepartmentEntity)

        when:
        def result = DepartmentMapper.entitiesToViews(List.of(entity))

        then:
        result.get(0).id == entity.id
        result.get(0).name == entity.name
    }
}
