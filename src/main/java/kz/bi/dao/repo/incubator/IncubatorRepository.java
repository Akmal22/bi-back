package kz.bi.dao.repo.incubator;

import kz.bi.dao.entity.CountryEntity;
import kz.bi.dao.entity.incubator.IncubatorEntity;
import kz.bi.dao.entity.user.UserEntity;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IncubatorRepository extends JpaRepository<IncubatorEntity, Long> {
    List<IncubatorEntity> findByCountry(CountryEntity country);

    List<IncubatorEntity> findByManager(UserEntity manager);

    @EntityGraph(value = "IncubatorEntity.withAllRelations")
    Optional<IncubatorEntity> findByUuid(String uuid);

    boolean existsByName(String name);

    @EntityGraph(value = "IncubatorEntity.withAllRelations")
    Optional<IncubatorEntity> findWithAllRelationsByUuid(String uuid);
}
