package kz.bi.dao.entity.incubator;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.math.BigDecimal;

@Entity
@Table(name = "incubator_expenses")
@Data
@ToString(exclude = "incubator")
@EqualsAndHashCode(exclude = "incubator")
public class IncubatorExpenseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "incubator_id", nullable = false)
    private IncubatorEntity incubator;

    @Column(name = "payroll", nullable = false)
    private BigDecimal payroll;

    @Column(name = "equipment", nullable = false)
    private BigDecimal equipment;

    @Column(name = "utilities", nullable = false)
    private BigDecimal utilities;

    @Column(name = "tax", nullable = false)
    private BigDecimal tax;

    @Column(name = "rents", nullable = false)
    private BigDecimal rents;

    @Column(name = "bank_repayments", nullable = false)
    private BigDecimal bankRepayments;

    @Column(name = "material", nullable = false)
    private BigDecimal material;

    @Column(name = "insurance", nullable = false)
    private BigDecimal insurance;
}
