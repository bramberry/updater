package by.vsu.bramberry.updateChecker.model.entity.hardware;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.common.base.Objects;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "processor")
public class Processor implements Serializable {

    private Long id;
    private Integer amountOfCores;
    private String cpu;
    @JsonIgnore
    private Hardware hardware;
    private Boolean virtualizationFirmwareEnabled;

    public Processor() {
    }

    public Processor(Long id, Integer amountOfCores, String cpu, Boolean virtualizationFirmwareEnabled) {
        this.id = id;
        this.amountOfCores = amountOfCores;
        this.cpu = cpu;
        this.virtualizationFirmwareEnabled = virtualizationFirmwareEnabled;
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

    @Column(name = "amount_of_cores")
    public Integer getAmountOfCores() {
        return amountOfCores;
    }

    public void setAmountOfCores(Integer amountOfCores) {
        this.amountOfCores = amountOfCores;
    }

    @Column(name = "cpu")
    public String getCpu() {
        return cpu;
    }

    public void setCpu(String cpu) {
        this.cpu = cpu;
    }

    @Column(name = "virtualization")
    public Boolean getVirtualizationFirmwareEnabled() {
        return virtualizationFirmwareEnabled;
    }

    public void setVirtualizationFirmwareEnabled(Boolean virtualizationFirmwareEnabled) {
        this.virtualizationFirmwareEnabled = virtualizationFirmwareEnabled;
    }

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "processor")
    public Hardware getHardware() {
        return hardware;
    }

    public void setHardware(Hardware hardware) {
        this.hardware = hardware;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Processor processor = (Processor) o;
        return Objects.equal(id, processor.id) &&
                Objects.equal(cpu, processor.cpu) &&
                Objects.equal(virtualizationFirmwareEnabled, processor.virtualizationFirmwareEnabled) &&
                Objects.equal(amountOfCores, processor.amountOfCores);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id, cpu, amountOfCores, virtualizationFirmwareEnabled);
    }

    @Override
    public String toString() {
        return "Processor{" +
                "id=" + id +
                ", amountOfCores=" + amountOfCores +
                ", cpu='" + cpu + '\'' +
                ", virtualizationFirmwareEnabled=" + virtualizationFirmwareEnabled +
                '}';
    }
}
