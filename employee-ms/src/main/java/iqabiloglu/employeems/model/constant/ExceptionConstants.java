package iqabiloglu.employeems.model.constant;

public final class ExceptionConstants {

    public static final String UNEXPECTED_EXCEPTION_CODE = "UNEXPECTED EXCEPTION";
    public static final String UNEXPECTED_EXCEPTION_MESSAGE = "Unexpected exception occured";
    public static final String VALIDATION_EXCEPTION_CODE = "VALIDATION_EXCEPTION";
    public static final String VALIDATION_EXCEPTION_MESSAGE = "One or more required fields in the request are invalid";

    public static final String DEPARTMENT_NOT_FOUND_CODE = "DEPARTMENT_NOT_FOUND";
    public static final String DEPARTMENT_NOT_FOUND_MESSAGE = "Department with id: %s not found";
    public static final String DEPARTMENTS_NOT_FOUND_MESSAGE = "There is not any department";
    public static final String DEPARTMENT_ALREADY_EXIST_CODE = "DEPARTMENT_ALREADY_EXIST";
    public static final String DEPARTMENT_ALREADY_EXIST_MESSAGE = "Department with this name: %s already exist";
    public static final String DEPARTMENT_NOT_EMPTY_CODE = "DEPARTMENT_NOT_EMPTY";
    public static final String DEPARTMENT_NOT_EMPTY_MESSAGE = "Department with this id: %s not empty";

    public static final String POSITION_NOT_FOUND_CODE = "POSITION_NOT_FOUND";
    public static final String POSITION_NOT_FOUND_MESSAGE = "Position with id: %s not found";
    public static final String POSITIONS_NOT_FOUND_MESSAGE = "There is not any position";
    public static final String POSITIONS_BY_DEPARTMET_NOT_FOUND_MESSAGE = "There is not any position with department name: %s";
    public static final String POSITION_ALREADY_EXIST_CODE = "POSITION_ALREADY_EXIST";
    public static final String POSITION_ALREADY_EXIST_MESSAGE = "Position with this name: %s already exist";
    public static final String POSITION_NOT_EMPTY_CODE = "POSITION_NOT_EMPTY";
    public static final String POSITION_NOT_EMPTY_MESSAGE = "Position with this id: %s not empty";

    public static final String EMPLOYEE_NOT_FOUND_CODE = "EMPLOYEE_NOT_FOUND";
    public static final String EMPLOYEE_NOT_FOUND_MESSAGE = "Employee with id: %s not found";
    public static final String EMPLOYEES_NOT_FOUND_MESSAGE = "There is not any employee";
    public static final String EMPLOYEES_BY_POSITION_NOT_FOUND_MESSAGE = "There is not any employee with position name: %s";
    public static final String EMPLOYEE_ALREADY_EXIST_CODE = "EMPLOYEE_ALREADY_EXIST";
    public static final String EMPLOYEE_ALREADY_EXIST_MESSAGE = "Employee with this email: %s already exist";
}
