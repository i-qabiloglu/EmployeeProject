package iqabiloglu.employeems.dao.repository;

import iqabiloglu.employeems.dao.entity.DepartmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DepartmentRepository extends JpaRepository<DepartmentEntity, Long> {

    List<DepartmentEntity> findAllByIsDeletedFalse();

    Optional<DepartmentEntity> findByIdAndIsDeletedFalse(Long id);
}
