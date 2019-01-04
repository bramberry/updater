package by.vsu.bramberry.updateChecker.model.dao;

import by.vsu.bramberry.updateChecker.model.entity.Computer;
import by.vsu.bramberry.updateChecker.model.entity.hardware.Hardware;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HardwareDao extends JpaRepository<Hardware, Long> {
    Hardware findByComputer(Computer computer);
}
