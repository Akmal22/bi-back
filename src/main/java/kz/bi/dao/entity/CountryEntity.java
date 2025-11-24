package kz.bi.dao.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "country")
public class CountryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "country_code", nullable = false, unique = true)
    private String countryCode;

    @Column(name = "country_name", nullable = false, unique = true)
    private String countryName;

    @Column(name = "currency_code", nullable = false)
    private Integer currencyCode;

    @Column(name = "currency_name", nullable = false)
    private String currencyName;
}
