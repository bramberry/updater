package by.vsu.bramberry.updateChecker.model.entity;

import by.vsu.bramberry.updateChecker.model.entity.hardware.Hardware;
import by.vsu.bramberry.updateChecker.model.entity.software.Software;
import com.google.common.base.Objects;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "computer")
public class Computer implements Serializable {

    private Long id;
    private String ip;
    private String audienceNumber;
    private String mac;
    private String description;
    private String systemUnitInvNumber;
    private String monitorInvNumber;
    private Set<Software> softwareSet = new HashSet<>();
    private Hardware hardware;


    public Computer() {
    }

    public Computer(String ip, String audienceNumber,
                    String mac, String description,
                    String systemUnitInvNumber,
                    String monitorInvNumber,
                    Set<Software> softwareSet) {
        this.ip = ip;
        this.audienceNumber = audienceNumber;
        this.mac = mac;
        this.description = description;
        this.systemUnitInvNumber = systemUnitInvNumber;
        this.monitorInvNumber = monitorInvNumber;
        this.softwareSet = softwareSet;
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

    @Column(name = "ip", unique = true)
    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    @Column(name = "audience_number")
    public String getAudienceNumber() {
        return audienceNumber;
    }

    public void setAudienceNumber(String audienceNumber) {
        this.audienceNumber = audienceNumber;
    }

    @Column(name = "mac")
    public String getMac() {
        return mac;
    }

    public void setMac(String mac) {
        this.mac = mac;
    }

    @Column(name = "system_unit_inv_number")
    public String getSystemUnitInvNumber() {
        return systemUnitInvNumber;
    }

    public void setSystemUnitInvNumber(String systemUnitInvNumber) {
        this.systemUnitInvNumber = systemUnitInvNumber;
    }

    @Column(name = "monitor_inv_number")
    public String getMonitorInvNumber() {
        return monitorInvNumber;
    }

    public void setMonitorInvNumber(String monitorInvNumber) {
        this.monitorInvNumber = monitorInvNumber;
    }

    @Column(name = "description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "computer")
    public Hardware getHardware() {
        return hardware;
    }

    public void setHardware(Hardware hardware) {
        this.hardware = hardware;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "computer")
    public Set<Software> getSoftwareSet() {
        return softwareSet;
    }

    public void setSoftwareSet(Set<Software> softwareSet) {
        this.softwareSet = softwareSet;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Computer computer = (Computer) o;
        return Objects.equal(id, computer.id) &&
                Objects.equal(ip, computer.ip) &&
                Objects.equal(audienceNumber, computer.audienceNumber) &&
                Objects.equal(mac, computer.mac) &&
                Objects.equal(description, computer.description) &&
                Objects.equal(systemUnitInvNumber, computer.systemUnitInvNumber) &&
                Objects.equal(monitorInvNumber, computer.monitorInvNumber) &&
                Objects.equal(softwareSet, computer.softwareSet);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id, ip, audienceNumber, mac,
                description, softwareSet, monitorInvNumber, systemUnitInvNumber);
    }

    @Override
    public String toString() {
        return "Computer{" +
                "id=" + id +
                ", ip='" + ip + '\'' +
                ", audienceNumber='" + audienceNumber + '\'' +
                ", mac='" + mac + '\'' +
                ", description='" + description + '\'' +
                ", systemUnitInvNumber='" + systemUnitInvNumber + '\'' +
                ", monitorInvNumber='" + monitorInvNumber + '\'' +
                ", softwareSet=" + softwareSet +
                ", hardware=" + hardware +
                '}';
    }
}
