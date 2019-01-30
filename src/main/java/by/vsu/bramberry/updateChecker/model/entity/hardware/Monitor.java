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
@Table(name = "monitor")
@NoArgsConstructor
@ToString
@Getter
@Setter
public class Monitor implements Serializable {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String diagonal;
    private String model;
    @JsonIgnore
    @OneToOne(fetch = FetchType.LAZY, mappedBy = "monitor")
    private Hardware hardware;

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
}
