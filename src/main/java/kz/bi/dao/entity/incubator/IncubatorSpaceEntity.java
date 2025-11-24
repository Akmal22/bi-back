package kz.bi.dao.entity.incubator;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "incubator_space")
public class IncubatorSpaceEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "incubator_id", nullable = false)
    private IncubatorEntity incubator;

    @Column(name = "overall_space", nullable = false)
    private Integer overallSpace;

    @Column(name = "avg_resident_space", nullable = false)
    private Integer avgResidentSpace;

    @Column(name = "communal_space", nullable = false)
    private Integer communalSpace;

    @Column(name = "admin_space", nullable = false)
    private Integer adminSpace;

    @Column(name = "communal_space_ratio", nullable = false)
    private Integer communalSpaceRatio;
}
