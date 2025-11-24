package kz.bi.dao.entity.incubator;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;

@Entity
@Table(name = "incubator_investment")
@Data
public class IncubatorInvestmentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "incubator_id", nullable = false)
    private IncubatorEntity incubator;

    @Column(name = "seed", nullable = false)
    private BigDecimal seed;

    @Column(name = "state", nullable = false)
    private BigDecimal state;

    @Column(name = "privates", nullable = false)
    private BigDecimal privates;

    @Column(name = "current_year_investment", nullable = false)
    private BigDecimal currentYearInvestment;

    @Column(name = "cumulative_investment", nullable = false)
    private BigDecimal cumulativeInvestment;
}
