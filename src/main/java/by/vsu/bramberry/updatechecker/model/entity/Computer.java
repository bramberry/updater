package by.vsu.bramberry.updatechecker.model.entity;

import by.vsu.bramberry.updatechecker.model.entity.hardware.Hardware;
import by.vsu.bramberry.updatechecker.model.entity.software.Software;
import com.google.common.base.Objects;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.Indexed;

import java.util.Set;

@Data
public class Computer {
    @Transient
    public static final String SEQUENCE_NAME = "computer_sequence";

    @Id
    private Long id;
    @Indexed(unique = true)
    private String ip;
    private String audienceNumber;
    private String mac;
    private String description;
    @Indexed(unique = true)
    private String systemUnitInvNumber;
    @Indexed(unique = true)
    private String monitorInvNumber;
    private Set<Software> softwareSet;
    private Hardware hardware;

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
                Objects.equal(monitorInvNumber, computer.monitorInvNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id, ip, audienceNumber, mac, description, systemUnitInvNumber, monitorInvNumber);
    }

}
