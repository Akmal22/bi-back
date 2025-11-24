package kz.bi.dao.repo.incubator;

import kz.bi.dao.entity.incubator.IncubatorInfrastructureEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IncubatorInfrastructureRepository extends JpaRepository<IncubatorInfrastructureEntity, Long> {

}
