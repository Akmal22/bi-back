package kz.bi.dao.repo.incubator;

import kz.bi.dao.entity.incubator.IncubatorSpaceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IncubatorSpaceRepository extends JpaRepository<IncubatorSpaceEntity, Long> {
}
