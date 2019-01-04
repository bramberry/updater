package by.vsu.bramberry.updateChecker.model.entity.hardware;

import by.vsu.bramberry.updateChecker.model.entity.Computer;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.google.common.base.Objects;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "hardware")
public class Hardware implements Serializable {

    private Long id;
    private Set<Hdd> hddSet = new HashSet<>(0);
    private Set<Ram> ramSet = new HashSet<>(0);
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Processor processor;
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Monitor monitor;
    @JsonIgnore
    private Computer computer;

    public Hardware() {
    }

    public Hardware(Set<Hdd> hddSet, Set<Ram> ramSet, Monitor monitor) {
        this.hddSet = hddSet;
        this.ramSet = ramSet;
        this.monitor = monitor;
    }

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @OneToOne(fetch = FetchType.EAGER)
    public Processor getProcessor() {
        return processor;
    }

    public void setProcessor(Processor processor) {
        this.processor = processor;
    }

    @OneToOne(fetch = FetchType.EAGER)
    public Monitor getMonitor() {
        return monitor;
    }

    public void setMonitor(Monitor monitor) {
        this.monitor = monitor;
    }

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "hardware", cascade = CascadeType.ALL)
    public Set<Hdd> getHddSet() {
        return hddSet;
    }

    public void setHddSet(Set<Hdd> hddSet) {
        this.hddSet = hddSet;
    }

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "hardware", cascade = CascadeType.ALL)
    public Set<Ram> getRamSet() {
        return ramSet;
    }

    public void setRamSet(Set<Ram> ramSet) {
        this.ramSet = ramSet;
    }

    @OneToOne(fetch = FetchType.LAZY)
    public Computer getComputer() {
        return computer;
    }

    public void setComputer(Computer computer) {
        this.computer = computer;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Hardware hardware = (Hardware) o;
        return Objects.equal(id, hardware.id) &&
                Objects.equal(hddSet, hardware.hddSet) &&
                Objects.equal(ramSet, hardware.ramSet);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id, hddSet, ramSet);
    }

    @Override
    public String toString() {
        return "{" +
                "id=" + id +
                ", hddSet=" + hddSet +
                ", ramSet=" + ramSet +
                ", processor=" + processor +
                '}';
    }
}
