package kz.bi.dao.entity.incubator;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;

@Table(name = "incubator_projects")
@Entity
@Data
public class IncubatorProjectsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "incubator_id", nullable = false)
    private IncubatorEntity incubator;

    @Column(name = "year", unique = true)
    private int year;

    @Column(name = "projects_count")
    private int projectsCount;

    @Column(name = "fund")
    private BigDecimal fund;
}
