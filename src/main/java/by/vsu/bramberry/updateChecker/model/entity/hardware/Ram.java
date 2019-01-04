package by.vsu.bramberry.updateChecker.model.entity.hardware;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.common.base.Objects;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "ram")
public class Ram implements Serializable {

    private Long id;
    private String volume;
    private String model;
    private String technicalSpeed;
    private String serialNumber;
    @JsonIgnore
    private Hardware hardware;


    public Ram() {
    }

    public Ram(String volume, String model, String technicalSpeed, String serialNumber) {
        this.volume = volume;
        this.model = model;
        this.technicalSpeed = technicalSpeed;
        this.serialNumber = serialNumber;
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

    @Column(name = "volume", nullable = false)
    public String getVolume() {
        return volume;
    }

    public void setVolume(String volume) {
        this.volume = volume;
    }

    @Column(name = "model")
    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    @Column(name = "technical_speed")
    public String getTechnicalSpeed() {
        return technicalSpeed;
    }

    public void setTechnicalSpeed(String technicalSpeed) {
        this.technicalSpeed = technicalSpeed;
    }

    @Column(name = "serial_number", unique = true)
    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hardware_id", nullable = false)
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

    @Override
    public String toString() {
        return "Ram{" +
                "id=" + id +
                ", volume='" + volume + '\'' +
                ", model='" + model + '\'' +
                ", technicalSpeed='" + technicalSpeed + '\'' +
                ", serialNumber='" + serialNumber + '\'' +
                '}';
    }
}
