package iqabiloglu.employeems.dao.repository;

import iqabiloglu.employeems.dao.entity.DepartmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface DepartmentRepository extends JpaRepository<DepartmentEntity, Long> {

    @Query(nativeQuery = true, value = "select * from departments where is_deleted = false")
    Set<DepartmentEntity> findAllDepartments();

    Optional<DepartmentEntity> findByIdAndIsDeletedFalse(Long id);
}