package by.vsu.bramberry.updateChecker.model.dao;

import by.vsu.bramberry.updateChecker.model.entity.software.Software;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SoftwareDao extends JpaRepository<Software, Long> {
}
