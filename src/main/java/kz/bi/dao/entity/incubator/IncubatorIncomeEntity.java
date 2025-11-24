package kz.bi.dao.entity.incubator;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;

@Entity
@Table(name = "incubator_income")
@Data
public class IncubatorIncomeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "incubator_id", nullable = false)
    private IncubatorEntity incubator;

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
