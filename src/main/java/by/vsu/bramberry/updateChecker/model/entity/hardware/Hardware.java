package by.vsu.bramberry.updateChecker.model.entity.hardware;

import by.vsu.bramberry.updateChecker.model.entity.Computer;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.google.common.base.Objects;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "hardware")
@NoArgsConstructor
@ToString
@Getter
@Setter
public class Hardware implements Serializable {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "hardware", cascade = CascadeType.REMOVE)
    private Set<Hdd> hddSet = new HashSet<>(0);
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "hardware", cascade = CascadeType.REMOVE)
    private Set<Ram> ramSet = new HashSet<>(0);
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    private Processor processor;
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    private Monitor monitor;
    @JsonIgnore
    @OneToOne(fetch = FetchType.LAZY)
    private Computer computer;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Hardware hardware = (Hardware) o;
        return Objects.equal(id, hardware.id) &&
                Objects.equal(hddSet, hardware.hddSet) &&
                Objects.equal(ramSet, hardware.ramSet) &&
                Objects.equal(processor, hardware.processor) &&
                Objects.equal(monitor, hardware.monitor) &&
                Objects.equal(computer, hardware.computer);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id, hddSet, ramSet, processor, monitor);
    }
}
