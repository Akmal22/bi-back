package kz.bi.dao.repo.incubator;

import kz.bi.dao.entity.incubator.IncubatorResidentsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IncubatorResidentsRepository extends JpaRepository<IncubatorResidentsEntity, Long> {
}
