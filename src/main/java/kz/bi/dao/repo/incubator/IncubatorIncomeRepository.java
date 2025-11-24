package kz.bi.dao.repo.incubator;

import kz.bi.dao.entity.incubator.IncubatorIncomeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IncubatorIncomeRepository extends JpaRepository<IncubatorIncomeEntity, Long> {
}
