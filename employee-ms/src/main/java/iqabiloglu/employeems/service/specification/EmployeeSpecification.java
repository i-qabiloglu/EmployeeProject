package iqabiloglu.employeems.service.specification;


import iqabiloglu.employeems.dao.entity.EmployeeEntity;
import iqabiloglu.employeems.dao.entity.PositionEntity;
import iqabiloglu.employeems.model.criteria.EmployeeCriteria;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@RequiredArgsConstructor
public class EmployeeSpecification implements Specification<EmployeeEntity> {

    private final EmployeeCriteria criteria;

    @Override
    public Predicate toPredicate(Root<EmployeeEntity> root, CriteriaQuery<?> query,
            CriteriaBuilder criteriaBuilder) {

        List<Predicate> predicates = new ArrayList<>();

        if (criteria != null) {

            if (criteria.getFullName() != null) {

                predicates.add(criteriaBuilder.like(root.get("firstName"),
                                                    "%" + criteria.getFullName().trim()
                                                                  .toUpperCase(Locale.ROOT) + "%"));

            }

            if (criteria.getDepartmentId() != null) {

                Join<EmployeeEntity, PositionEntity> employeePosition = root.join("position");

                predicates.add(criteriaBuilder.equal(employeePosition.get("department"),
                                                     criteria.getDepartmentId()));
            }

            if (criteria.getPositionId() != null) {
                predicates.add(
                        criteriaBuilder.equal(root.get("position"), criteria.getPositionId()));
            }

            if (criteria.getGender() != null) {
                predicates.add(criteriaBuilder.equal(root.get("gender"), criteria.getGender()));
            }
        }
        predicates.add(criteriaBuilder.notEqual(root.get("isDeleted"), true));

        return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));

    }
}
