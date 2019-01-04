package by.vsu.bramberry.updateChecker.model.dao;

import by.vsu.bramberry.updateChecker.model.entity.hardware.Ram;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RamDao extends JpaRepository<Ram, Long> {
}
