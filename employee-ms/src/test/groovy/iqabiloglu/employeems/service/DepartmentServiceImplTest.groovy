package iqabiloglu.employeems.service

import io.github.benas.randombeans.EnhancedRandomBuilder
import iqabiloglu.employeems.dao.entity.DepartmentEntity
import iqabiloglu.employeems.dao.repository.DepartmentRepository
import iqabiloglu.employeems.dao.repository.PositionRepository
import iqabiloglu.employeems.model.dto.DepartmentDto
import iqabiloglu.employeems.model.exception.AlreadyExistException
import iqabiloglu.employeems.model.exception.NotEmptyException
import iqabiloglu.employeems.model.exception.NotFoundException
import iqabiloglu.employeems.service.impl.DepartmentServiceImpl
import spock.lang.Specification
import io.github.benas.randombeans.api.EnhancedRandom


class DepartmentServiceImplTest extends Specification {

    private DepartmentServiceImpl service
    private DepartmentRepository departmentRepository
    private PositionRepository positionRepository
    private EnhancedRandom random = EnhancedRandomBuilder.aNewEnhancedRandom()

    void setup() {
        departmentRepository = Mock()
        positionRepository = Mock()
        service = new DepartmentServiceImpl(departmentRepository, positionRepository)
    }

    def "GetList with success"() {

        given:
        def departmenList = random.objects(DepartmentEntity, 5)

        when:
        def result = service.getList()

        then:
        1 * departmentRepository.findAllByIsDeletedFalse() >> departmenList
        result != null
    }

    def "GetList with error"() {

        given:
        def departmenList = random.objects(DepartmentEntity, 0)

        when:
        def result = service.getList()

        then:
        1 * departmentRepository.findAllByIsDeletedFalse() >> departmenList
        NotFoundException ex = thrown()
        ex.message == "There is not any department"
        ex.code == "DEPARTMENT_NOT_FOUND"
    }

    def "Get with success"() {

        given:
        def id = 1L
        def entity = random.nextObject(DepartmentEntity)

        when:
        def result = service.get(id)

        then:
        1 * departmentRepository.findByIdAndIsDeletedFalse(id) >> Optional.of(entity)
        result != null

    }

    def "Get with error"() {

        given:
        def id = 1L
        def entity = random.nextObject(DepartmentEntity)

        when:
        def result = service.get(id)

        then:
        1 * departmentRepository.findByIdAndIsDeletedFalse(id) >> Optional.empty()
        NotFoundException ex = thrown()
        ex.message == "Department with id: 1 not found"
        ex.code == "DEPARTMENT_NOT_FOUND"
//        thrown(NotFoundException)
    }

    def "Create with success"() {

        given:
        def dto = random.nextObject(DepartmentDto)
        def entity = random.nextObject(DepartmentEntity)
        dto.name = "abc"
        entity.name = "ab"

        when:
        service.create(dto)

        then:
        1 * departmentRepository.findAllByIsDeletedFalse() >> List.of(entity)
        1 * departmentRepository.save(_)


    }

    def "Create with AlreadyExist error"() {

        given:
        def dto = random.nextObject(DepartmentDto)
        def entity = random.nextObject(DepartmentEntity)
        dto.name = "abc"
        entity.name = "abc"

        when:
        service.create(dto)

        then:
        1 * departmentRepository.findAllByIsDeletedFalse() >> List.of(entity)

        AlreadyExistException ex = thrown()
        ex.code == "DEPARTMENT_ALREADY_EXIST"
        ex.message == "Department with this name: abc already exist"

    }

    def "Update with success"() {

        given:
        def id = 1L
        def dto = Mock(DepartmentDto)
        def entity = random.nextObject(DepartmentEntity)

        when:
        service.update(id, dto)

        then:
        1 * departmentRepository.findByIdAndIsDeletedFalse(id) >> Optional.of(entity)
        1 * departmentRepository.save(_)

    }

    def "Update with error"() {
        given:
        def id = 1L
        def dto = Mock(DepartmentDto)
        def entity = random.nextObject(DepartmentEntity)

        when:
        service.update(id, dto)

        then:
        1 * departmentRepository.findByIdAndIsDeletedFalse(id) >> Optional.empty()
        thrown(NotFoundException)
    }

    def "Delete with success"() {

        given:
        def id = 1L
        def entity = random.nextObject(DepartmentEntity)

        when:
        service.delete(id)

        then:
        1 * departmentRepository.findByIdAndIsDeletedFalse(id) >> Optional.of(entity)
        1 * positionRepository.findAllByIsDeletedFalseAndDepartment_Id(id) >> List.of()
        1 * departmentRepository.save(_)

    }

    def "Delete with error"() {

        given:
        def id = 1L
        def entity = random.nextObject(DepartmentEntity)

        when:
        service.delete(id)

        then:
        1 * departmentRepository.findByIdAndIsDeletedFalse(id) >> Optional.of(entity)
        1 * positionRepository.findAllByIsDeletedFalseAndDepartment_Id(id) >> List.of(entity)
        thrown(NotEmptyException)

    }


}
