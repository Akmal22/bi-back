package kz.bi.dao.entity.incubator;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.math.BigDecimal;

@Entity
@Table(name = "incubator_income")
@Data
@ToString(exclude = "incubator")
@EqualsAndHashCode(exclude = "incubator")
public class IncubatorIncomeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "incubator_id", nullable = false)
    private IncubatorEntity incubator;

    @Column(name = "year", nullable = false)
    private Integer year;

    @Column(name = "initial_capital", nullable = false)
    private BigDecimal initialCapital;

    @Column(name = "paid_services_income", nullable = false)
    private BigDecimal paidServicesIncome;

    @Column(name = "paid_training_income", nullable = false)
    private BigDecimal paidTrainingIncome;

    @Column(name = "paid_facilities_income", nullable = false)
    private BigDecimal paidFacilitiesIncome;

    @Column(name = "donors", nullable = false)
    private BigDecimal donors;

    @Column(name = "state", nullable = false)
    private BigDecimal state;

    @Column(name = "loans", nullable = false)
    private BigDecimal loans;
}
