package by.vsu.bramberry.updateChecker.model.entity.hardware;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "hdd")
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@Getter
@Setter
public class Hdd implements Serializable {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "disk_name", nullable = false)
    private String diskName;
    private String model;
    @Column(name = "fragmentation", nullable = false)
    private String fragmentation;
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hardware_id", nullable = false)
    private Hardware hardware;
}
