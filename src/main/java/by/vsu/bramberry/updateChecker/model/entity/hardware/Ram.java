package by.vsu.bramberry.updateChecker.model.entity.hardware;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.common.base.Objects;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "ram")
@NoArgsConstructor
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ram ram = (Ram) o;
        return Objects.equal(id, ram.id) &&
                Objects.equal(volume, ram.volume) &&
                Objects.equal(model, ram.model) &&
                Objects.equal(technicalSpeed, ram.technicalSpeed) &&
                Objects.equal(serialNumber, ram.serialNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id, volume, model, technicalSpeed, serialNumber);
    }
}
