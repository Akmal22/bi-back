package kz.bi.dao.entity.incubator;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.math.BigDecimal;

@Table(name = "incubator_projects")
@Entity
@Data
@ToString(exclude = "incubator")
@EqualsAndHashCode(exclude = "incubator")
public class IncubatorProjectsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "incubator_id", nullable = false)
    private IncubatorEntity incubator;

    @Column(name = "year", nullable = false)
    private int year;

    @Column(name = "projects_count")
    private int projectsCount;

    @Column(name = "fund")
    private BigDecimal fund;
}
