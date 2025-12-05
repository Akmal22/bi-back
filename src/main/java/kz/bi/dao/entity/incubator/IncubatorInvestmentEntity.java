package kz.bi.dao.entity.incubator;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.math.BigDecimal;

@Entity
@Table(name = "incubator_investment")
@Data
@ToString(exclude = "incubator")
@EqualsAndHashCode(exclude = "incubator")
public class IncubatorInvestmentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "incubator_id", nullable = false)
    private IncubatorEntity incubator;

    @Column(name = "year", nullable = false)
    private Integer year;

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
