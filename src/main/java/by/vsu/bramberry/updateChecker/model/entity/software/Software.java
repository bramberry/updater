package by.vsu.bramberry.updateChecker.model.entity.software;

import by.vsu.bramberry.updateChecker.model.entity.Computer;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.common.base.Objects;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "software")
@NoArgsConstructor
@Getter
@Setter
public class Software implements Serializable {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String currentVersion;
    private String previousVersion;
    private Boolean actualVersion;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy.MM.dd HH:mm")
    private Date installDate;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy.MM.dd HH:mm")
    private Date updateDate;
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "computer_id", nullable = false)
    private Computer computer;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Software software = (Software) o;
        return Objects.equal(id, software.id) &&
                Objects.equal(name, software.name) &&
                Objects.equal(currentVersion, software.currentVersion) &&
                Objects.equal(previousVersion, software.previousVersion) &&
                Objects.equal(actualVersion, software.actualVersion) &&
                Objects.equal(installDate, software.installDate) &&
                Objects.equal(updateDate, software.updateDate);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id, name, currentVersion, previousVersion, actualVersion, installDate, updateDate);
    }
}
