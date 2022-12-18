package iqabiloglu.employeems.dao.repository;

import iqabiloglu.employeems.dao.entity.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<EmployeeEntity, Long> {

    @Query(nativeQuery = true, value = "select * from employees where is_deleted =false")
    List<EmployeeEntity> findAll();

    List<EmployeeEntity> findAllByPosition_IdAndIsDeletedFalse(Long positionId);

    Optional<EmployeeEntity> findByIdAndIsDeletedFalse(Long id);
}
