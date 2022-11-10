package iqabiloglu.employeems.dao.repository;

import iqabiloglu.employeems.dao.entity.PositionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface PositionRepository extends JpaRepository<PositionEntity, Long> {

    @Query(nativeQuery = true, value = "select * from positions where is_deleted = false")
    Set<PositionEntity> findAllPositions();

    Optional<PositionEntity> findByIdAndIsDeletedFalse(Long id);
}
