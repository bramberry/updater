package by.vsu.bramberry.updateChecker.model.dao;

import by.vsu.bramberry.updateChecker.model.entity.hardware.Monitor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MonitorDao extends JpaRepository<Monitor, Long> {
}
