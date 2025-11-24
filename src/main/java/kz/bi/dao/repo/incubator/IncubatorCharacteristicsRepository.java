package kz.bi.dao.repo.incubator;

import kz.bi.dao.entity.incubator.IncubatorCharacteristicsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IncubatorCharacteristicsRepository extends JpaRepository<IncubatorCharacteristicsEntity, Long> {
}
