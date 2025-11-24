package kz.bi.dao.entity.incubator;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "incubator_characteristics")
@Data
public class IncubatorCharacteristicsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "incubator_id", nullable = false)
    private IncubatorEntity incubator;

    @Enumerated(EnumType.STRING)
    @Column(name = "average_employees", nullable = false)
    private AverageEmployeePerResident averageEmployeePerResident;

    @Enumerated(EnumType.STRING)
    @Column(name = "share_amount", nullable = false)
    private ShareAmount shareAmount;

    @Column(name = "total_staff", nullable = false)
    private Integer totalStaff;

    @Column(name = "experts_and_consultants", nullable = false)
    private Integer expertsAndConsultants;

    @Column(name = "managers", nullable = false)
    private Integer managers;

    @Column(name = "monitoring_and_data_collecting")
    private boolean monitoringAndDataCollecting;

    @Column(name = "requirements")
    private String requirements;
}
