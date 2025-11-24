package kz.bi.dao.repo.incubator;

import kz.bi.dao.entity.incubator.IncubatorExpenseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IncubatorExpenseRepository extends JpaRepository<IncubatorExpenseEntity, Long> {
}
