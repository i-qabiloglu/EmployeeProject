package iqabiloglu.employeems.model.enums;

import com.google.common.collect.Sets;

import java.util.Set;

import static iqabiloglu.employeems.model.enums.UserPermissions.*;

public enum UserRoles {

    ADMIN_ROLES(Sets.newHashSet(DEPARTMENT_READ, DEPARTMENT_WRITE, POSITION_READ, POSITION_WRITE,
                          EMPLOYEE_READ, EMPLOYEE_WRITE)),
    USER_ROLES(Sets.newHashSet(DEPARTMENT_READ, POSITION_READ, EMPLOYEE_WRITE, EMPLOYEE_READ));

    private final Set<UserPermissions> permissionsSets;

    UserRoles(Set<UserPermissions> permissionsSets) {
        this.permissionsSets = permissionsSets;
    }

    public Set<UserPermissions> getPermissionsSets( ) {
        return permissionsSets;
    }
}
