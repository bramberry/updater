package by.vsu.bramberry.updateChecker.model.entity.hardware;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "ram")
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@Getter
@Setter
public class Ram implements Serializable {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "volume", nullable = false)
    private String volume;
    private String model;
    private String technicalSpeed;
    @Column(name = "serial_number", unique = true)
    private String serialNumber;
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hardware_id", nullable = false)
    private Hardware hardware;
}
