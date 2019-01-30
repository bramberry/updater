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
@Table(name = "processor")
@NoArgsConstructor
@ToString
@Getter
@Setter
public class Processor implements Serializable {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Integer amountOfCores;
    private String cpu;
    @JsonIgnore
    @OneToOne(fetch = FetchType.LAZY, mappedBy = "processor")
    private Hardware hardware;
    private Boolean virtualizationFirmwareEnabled;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Processor processor = (Processor) o;
        return Objects.equal(id, processor.id) &&
                Objects.equal(amountOfCores, processor.amountOfCores) &&
                Objects.equal(cpu, processor.cpu) &&
                Objects.equal(virtualizationFirmwareEnabled, processor.virtualizationFirmwareEnabled);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id, amountOfCores, cpu, virtualizationFirmwareEnabled);
    }
}
