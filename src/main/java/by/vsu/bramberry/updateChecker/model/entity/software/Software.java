package by.vsu.bramberry.updateChecker.model.entity.software;

import by.vsu.bramberry.updateChecker.model.entity.Computer;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.common.base.Objects;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "software")
public class Software implements Serializable {
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
    private Computer computer;

    public Software() {
    }

    public Software(String name, String currentVersion, String previousVersion, Boolean actualVersion, Date installDate, Date updateDate) {
        this.name = name;
        this.currentVersion = currentVersion;
        this.previousVersion = previousVersion;
        this.actualVersion = actualVersion;
        this.installDate = installDate;
        this.updateDate = updateDate;

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

    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "current_version")
    public String getCurrentVersion() {
        return currentVersion;
    }

    public void setCurrentVersion(String currentVersion) {
        this.currentVersion = currentVersion;
    }

    @Column(name = "previous_version")
    public String getPreviousVersion() {
        return previousVersion;
    }

    public void setPreviousVersion(String previousVersion) {
        this.previousVersion = previousVersion;
    }

    @Column(name = "actual_version")
    public Boolean getActualVersion() {
        return actualVersion;
    }

    public void setActualVersion(Boolean actualVersion) {
        this.actualVersion = actualVersion;
    }

    @Column(name = "install_date")
    public Date getInstallDate() {
        return installDate;
    }

    public void setInstallDate(Date installDate) {
        this.installDate = installDate;
    }

    @Column(name = "update_date")
    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "computer_id", nullable = false)
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
        Software software = (Software) o;
        return Objects.equal(id, software.id) &&
                Objects.equal(name, software.name) &&
                Objects.equal(currentVersion, software.currentVersion) &&
                Objects.equal(previousVersion, software.previousVersion) &&
                Objects.equal(installDate, software.installDate) &&
                Objects.equal(updateDate, software.updateDate);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id, name, currentVersion, previousVersion, installDate, updateDate);
    }

    @Override
    public String toString() {
        return "Software{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", currentVersion='" + currentVersion + '\'' +
                ", previousVersion='" + previousVersion + '\'' +
                ", installDate=" + installDate +
                ", updateDate=" + updateDate +
                '}';
    }
}
