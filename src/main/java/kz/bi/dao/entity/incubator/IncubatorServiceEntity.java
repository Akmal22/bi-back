package kz.bi.dao.entity.incubator;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "incubator_services")
@Data
public class IncubatorServiceEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "incubator_id", nullable = false)
    private IncubatorEntity incubator;

    @Column(name = "offered_services", nullable = false)
    private Integer offeredServices;

    @Column(name = "free_services", nullable = false)
    private Integer freeServices;

    @Column(name = "used_services", nullable = false)
    private Integer usedServices;

    @Column(name = "paid_services", nullable = false)
    private Integer paidServices;

    @Column(name = "offered_facilities", nullable = false)
    private Integer offeredFacilities;

    @Column(name = "free_facilities", nullable = false)
    private Integer freeFacilities;

    @Column(name = "used_facilities", nullable = false)
    private Integer usedFacilities;

    @Column(name = "paid_facilities", nullable = false)
    private Integer paidFacilities;

    @Column(name = "offered_trainings", nullable = false)
    private Integer offeredTrainings;

    @Column(name = "free_trainings", nullable = false)
    private Integer freeTrainings;

    @Column(name = "used_trainings", nullable = false)
    private Integer usedTrainings;

    @Column(name = "paid_traingins", nullable = false)
    private Integer paidTrainings;
}
