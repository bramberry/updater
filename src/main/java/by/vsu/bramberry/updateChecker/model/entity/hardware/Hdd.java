package by.vsu.bramberry.updateChecker.model.entity.hardware;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "hdd")
@NoArgsConstructor
@Getter
@Setter
public class Hdd implements Serializable {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "disk_name", nullable = false)
    private String diskName;
    private String model;
    @Column(name = "fragmentation", nullable = false)
    private String fragmentation;
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hardware_id", nullable = false)
    private Hardware hardware;

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
        return MoreObjects.toStringHelper(this)
                .add("id", id)
                .add("diskName", diskName)
                .add("model", model)
                .add("fragmentation", fragmentation)
                .toString();
    }
}
