package iqabiloglu.employeems.model.enums;

public enum UserPermissions {

    DEPARTMENT_READ("department:read"),
    DEPARTMENT_WRITE("department:write"),
    POSITION_READ("position:read"),
    POSITION_WRITE("position_write"),
    EMPLOYEE_READ("employee:read"),
    EMPLOYEE_WRITE("employee:write");


    private final String permission;

    UserPermissions(String permission) {
        this.permission = permission;
    }

    public String getPermission( ) {
        return permission;
    }
}
