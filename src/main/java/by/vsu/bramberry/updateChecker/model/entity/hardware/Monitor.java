package by.vsu.bramberry.updateChecker.model.entity.hardware;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.common.base.Objects;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "monitor")
public class Monitor implements Serializable {
    private Long id;
    private String diagonal;
    private String model;
    @JsonIgnore
    private Hardware hardware;

    public Monitor() {
    }

    public Monitor(Long id, String diagonal, String model) {

        this.id = id;
        this.diagonal = diagonal;
        this.model = model;
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

    @Column(name = "diagonal")
    public String getDiagonal() {
        return diagonal;
    }

    public void setDiagonal(String diagonal) {
        this.diagonal = diagonal;
    }

    @Column(name = "model")
    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "monitor")
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
        Monitor monitor = (Monitor) o;
        return Objects.equal(id, monitor.id) &&
                Objects.equal(diagonal, monitor.diagonal) &&
                Objects.equal(model, monitor.model);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id, diagonal, model);
    }

    @Override
    public String toString() {
        return "Monitor{" +
                "id=" + id +
                ", diagonal='" + diagonal + '\'' +
                ", model='" + model + '\'' +
                '}';
    }
}
