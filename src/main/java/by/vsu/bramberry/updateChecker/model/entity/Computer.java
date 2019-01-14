package by.vsu.bramberry.updateChecker.model.entity;

import by.vsu.bramberry.updateChecker.model.entity.hardware.Hardware;
import by.vsu.bramberry.updateChecker.model.entity.software.Software;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "computer")
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@Getter
@Setter
public class Computer implements Serializable {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "ip", unique = true)
    private String ip;
    private String audienceNumber;
    private String mac;
    private String description;
    private String systemUnitInvNumber;
    private String monitorInvNumber;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "computer", cascade = CascadeType.REMOVE)
    private Set<Software> softwareSet = new HashSet<>();
    @OneToOne(fetch = FetchType.LAZY, mappedBy = "computer", cascade = CascadeType.REMOVE)
    private Hardware hardware;
}
