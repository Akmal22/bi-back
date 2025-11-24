package kz.bi.dao.entity.incubator;


import jakarta.persistence.*;
import kz.bi.dao.entity.CountryEntity;
import kz.bi.dao.entity.user.UserEntity;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@Table(name = "incubator")
@NamedEntityGraph(
    name = "IncubatorEntity.withAllRelations",
    attributeNodes = {
        @NamedAttributeNode("manager"),
        @NamedAttributeNode("country"),
        @NamedAttributeNode("incubatorCharacteristics"),
        @NamedAttributeNode("incubatorInfrastructure"),
        @NamedAttributeNode("incubatorSpace"),
        @NamedAttributeNode("incubatorResidents"),
        @NamedAttributeNode("incubatorServices"),
        @NamedAttributeNode("incubatorIncome"),
        @NamedAttributeNode("incubatorInvestment"),
        @NamedAttributeNode("incubatorExpense"),
        @NamedAttributeNode("incubatorProjects")
    }
)
public class IncubatorEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "uuid", length = 36, nullable = false, unique = true)
    private String uuid;

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @Column(name = "description", nullable = false)
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "manager", nullable = false)
    private UserEntity manager;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "country", nullable = false)
    private CountryEntity country;

    @OneToOne(mappedBy = "incubator", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private IncubatorCharacteristicsEntity incubatorCharacteristics;

    @OneToOne(mappedBy = "incubator", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private IncubatorInfrastructureEntity incubatorInfrastructure;

    @OneToOne(mappedBy = "incubator", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private IncubatorSpaceEntity incubatorSpace;

    @OneToOne(mappedBy = "incubator", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private IncubatorResidentsEntity incubatorResidents;

    @OneToOne(mappedBy = "incubator", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private IncubatorServiceEntity incubatorServices;

    @OneToOne(mappedBy = "incubator", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private IncubatorIncomeEntity incubatorIncome;

    @OneToOne(mappedBy = "incubator", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private IncubatorInvestmentEntity incubatorInvestment;

    @OneToOne(mappedBy = "incubator", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private IncubatorExpenseEntity incubatorExpense;

    @OneToMany(mappedBy = "incubator", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<IncubatorProjectsEntity> incubatorProjects;

    @Column(name = "founded", nullable = false)
    private LocalDateTime founded;
}
