package kz.bi.dao.repo;

import kz.bi.dao.entity.CountryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CountryRepository extends JpaRepository<CountryEntity, Long> {
    boolean existsByCountryName(String countryCode);
}
