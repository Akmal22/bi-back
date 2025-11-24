package kz.bi.dao.repo.incubator;

import kz.bi.dao.entity.incubator.IncubatorServiceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IncubatorServicesRepository extends JpaRepository<IncubatorServiceEntity, Long> {
}
