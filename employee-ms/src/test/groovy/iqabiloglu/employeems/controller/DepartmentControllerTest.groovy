package iqabiloglu.employeems.controller

import iqabiloglu.employeems.model.dto.DepartmentDto
import iqabiloglu.employeems.model.exception.AlreadyExistException
import iqabiloglu.employeems.model.exception.NotFoundException
import iqabiloglu.employeems.model.view.DepartmentView
import iqabiloglu.employeems.service.DepartmentService
import org.skyscreamer.jsonassert.JSONAssert
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import spock.lang.Specification

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get

class DepartmentControllerTest extends Specification {

    private DepartmentService service
    private DepartmentController controller
    private MockMvc mockMvc

    void setup() {
        service = Mock()
        controller = new DepartmentController(service)
        mockMvc = MockMvcBuilders.standaloneSetup(controller)
                .setControllerAdvice(new ErrorHandler())
                .build()
    }
    def id = 1L
    def endpoint = "/v1/departments/"

    def "get - succes 200"() {

        given:
        def view = DepartmentView.builder()
                .id(1L)
                .name("test")
                .build()

        def expectedResponse = '''{"id": 1,"name": "test"}'''

        when:
        def result = mockMvc.perform(get(endpoint + "1")
                .contentType(MediaType.APPLICATION_JSON))
                .andReturn()

        then:
        1 * service.get(id) >> view
        def response = result.response
        response.getStatus() == 200
        JSONAssert.assertEquals(expectedResponse, response.getContentAsString(), false)
    }

    def "get - error 404"() {

        given:
        def expectedResponse = '''{"code": "DEPARTMENT_NOT_FOUND","message": "Department with id: 1 not found"}'''

        when:
        def result = mockMvc.perform(get(endpoint + "1")
                .contentType(MediaType.APPLICATION_JSON))
                .andReturn()

        then:
        1 * service.get(id) >> { throw new NotFoundException("DEPARTMENT_NOT_FOUND", "Department with id: 1 not found") }
        def response = result.response
        response.getStatus() == 404
        JSONAssert.assertEquals(expectedResponse, result.response.getContentAsString(), false)
    }

    def "getList - success 200"() {

        given:
        def viewList = List.of(DepartmentView.builder()
                .id(1L)
                .name("test")
                .build())

        def expectedResponse = '''[{"id":1,"name":"test"}]'''

        when:
        def result = mockMvc.perform(get(endpoint)
                .contentType(MediaType.APPLICATION_JSON))
                .andReturn()

        then:
        1 * service.getList() >> viewList
        def response = result.response
        response.getStatus() == 200
        JSONAssert.assertEquals(expectedResponse, response.getContentAsString(), false)

    }

    def "getList - error 404"() {

        given:
        def expectedResponse = '''{"code": "DEPARTMENT_NOT_FOUND","message": "There is not any department"}'''

        when:
        def result = mockMvc.perform(get(endpoint)
                .contentType(MediaType.APPLICATION_JSON))
                .andReturn()

        then:
        1 * service.getList() >> { throw new NotFoundException("DEPARTMENT_NOT_FOUND", "There is not any department") }
        def response = result.response
        response.getStatus() == 404
        JSONAssert.assertEquals(expectedResponse, response.getContentAsString(), false)

    }

    def "create - succes 201"() {
    }

    def "create - error 404"() {

    }

    def "delete - success 204"() {


        when:
        def result = mockMvc.perform(delete(endpoint + "1")
                .contentType(MediaType.APPLICATION_JSON))
                .andReturn()

        then:
        1 * service.delete(id)
        def response = result.response
        response.getStatus() == 204


    }

    def "delete - error 404"() {

        given:
        def expectedResponse = '''{"code": "DEPARTMENT_NOT_FOUND","message": "Department with id: 1 not found"}'''

        when:
        def result = mockMvc.perform(delete(endpoint + "1")
                .contentType(MediaType.APPLICATION_JSON))
                .andReturn()

        then:
        1 * service.delete(id) >> { throw new NotFoundException("DEPARTMENT_NOT_FOUND", "Department with id: 1 not found") }
        def response = result.response
        response.getStatus() == 404
        JSONAssert.assertEquals(expectedResponse, result.response.getContentAsString(), false)


    }
}
