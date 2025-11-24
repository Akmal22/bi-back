package kz.bi.dao.repo.incubator;

import kz.bi.dao.entity.incubator.IncubatorInvestmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IncubatorInvestmentRepository extends JpaRepository<IncubatorInvestmentEntity, Long> {
}
