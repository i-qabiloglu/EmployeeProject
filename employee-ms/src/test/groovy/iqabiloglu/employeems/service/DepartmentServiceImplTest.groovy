package iqabiloglu.employeems.service

import io.github.benas.randombeans.EnhancedRandomBuilder
import iqabiloglu.employeems.dao.entity.DepartmentEntity
import iqabiloglu.employeems.dao.repository.DepartmentRepository
import iqabiloglu.employeems.dao.repository.PositionRepository
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

    }

    def "Create with AlreadyExist error"() {



    }

    def "Update"() {
    }

    def "Delete"() {
    }


}
