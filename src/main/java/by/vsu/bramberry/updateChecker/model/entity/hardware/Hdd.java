package by.vsu.bramberry.updateChecker.model.entity.hardware;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.common.base.Objects;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "hdd")
public class Hdd implements Serializable {

    private Long id;
    private String diskName;
    private String model;
    private String fragmentation;
    @JsonIgnore
    private Hardware hardware;

    public Hdd() {
    }

    public Hdd(String diskName, String model, String fragmentation) {
        this.diskName = diskName;
        this.model = model;
        this.fragmentation = fragmentation;
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

    @Column(name = "disk_name", nullable = false)
    public String getDiskName() {
        return diskName;
    }

    public void setDiskName(String diskName) {
        this.diskName = diskName;
    }

    @Column(name = "model")
    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    @Column(name = "fragmentation", nullable = false)
    public String getFragmentation() {
        return fragmentation;
    }

    public void setFragmentation(String fragmentation) {
        this.fragmentation = fragmentation;
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
        Hdd hdd = (Hdd) o;
        return Objects.equal(id, hdd.id) &&
                Objects.equal(diskName, hdd.diskName) &&
                Objects.equal(model, hdd.model) &&
                Objects.equal(fragmentation, hdd.fragmentation);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id, diskName, model, fragmentation);
    }

    @Override
    public String toString() {
        return "Hdd{" +
                "id=" + id +
                ", diskName='" + diskName + '\'' +
                ", model='" + model + '\'' +
                ", fragmentation='" + fragmentation +
                '}';
    }
}
