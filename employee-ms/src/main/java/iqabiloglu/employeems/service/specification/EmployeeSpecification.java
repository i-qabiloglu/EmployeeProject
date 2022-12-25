package iqabiloglu.employeems.service.specification;


import iqabiloglu.employeems.dao.entity.EmployeeEntity;
import iqabiloglu.employeems.model.criteria.EmployeeCriteria;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
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

                var firstNamePred = criteriaBuilder.like(
                        criteriaBuilder.lower(root.get("firstName")),
                        "%" + criteria.getFullName().trim().toLowerCase(Locale.ROOT) + "%");

                var lastNamePred = criteriaBuilder.like(
                        criteriaBuilder.lower(root.get("lastName")),
                        "%" + criteria.getFullName().trim().toLowerCase(Locale.ROOT) + "%");

                predicates.add(
                        criteriaBuilder.or(firstNamePred, criteriaBuilder.or(lastNamePred)));
            }


            if (criteria.getDepartmentId() != null) {

                predicates.add(criteriaBuilder.equal(root.join("position").get("department"),
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

        return criteriaBuilder.and(predicates.toArray(new Predicate[0]));

    }
}
