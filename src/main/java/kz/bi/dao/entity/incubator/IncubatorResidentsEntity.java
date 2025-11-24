package kz.bi.dao.entity.incubator;

import jakarta.persistence.*;
import lombok.Data;

@Table(name = "incubator_residents")
@Entity
@Data
public class IncubatorResidentsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "incubator_id", nullable = false)
    private IncubatorEntity incubator;

    @Column(name = "incubated_companies", nullable = false)
    private long incubatedCompanies;

    @Column(name = "failed_companies", nullable = false)
    private long failedCompanies;

    @Column(name = "graduated_companies", nullable = false)
    private long graduatedCompanies;

    @Column(name = "received_application", nullable = false)
    private long receivedApplication;

    @Column(name = "accepted_application", nullable = false)
    private long acceptedApplication;

    @Column(name = "active_after_3_months", nullable = false)
    private long activeAfter3Months;

    @Column(name = "active_after_6_months", nullable = false)
    private long activeAfter6Months;

    @Column(name = "active_after_1_year", nullable = false)
    private long activeAfter1Year;

    @Column(name = "active_after_3_years", nullable = false)
    private long activeAfter3Years;

    @Column(name = "active_after_5_year", nullable = false)
    private long activeAfter5Years;

    @Column(name = "failed_after_3_months", nullable = false)
    private long failedAfter3Months;

    @Column(name = "failed_after_6_months", nullable = false)
    private long failedAfter6Months;

    @Column(name = "failed_after_1_year", nullable = false)
    private long failedAfter1Year;

    @Column(name = "failed_after_3_years", nullable = false)
    private long failedAfter3Years;

    @Column(name = "failed_after_5_year", nullable = false)
    private long failedAfter5Years;
}
