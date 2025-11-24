package kz.bi.dao.entity.incubator;

import jakarta.persistence.*;
import lombok.Data;

@Table(name = "incubator_infrastructure")
@Entity
@Data
public class IncubatorInfrastructureEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "incubator_id", nullable = false)
    private IncubatorEntity incubator;

    @Column(name = "sectors_covered", nullable = false)
    private Integer sectorsCovered;

    @Column(name = "years_in_operation", nullable = false)
    private Integer yearsInOperation;

    @Column(name = "programme_duration", nullable = false)
    private Integer programmeDuration;
}
