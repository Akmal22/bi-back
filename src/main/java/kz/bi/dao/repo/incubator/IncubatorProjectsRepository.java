package kz.bi.dao.repo.incubator;

import kz.bi.dao.entity.incubator.IncubatorProjectsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IncubatorProjectsRepository extends JpaRepository<IncubatorProjectsEntity, Long> {
}
