package by.vsu.bramberry.updateChecker.model.dao;

import by.vsu.bramberry.updateChecker.model.entity.software.Software;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SoftwareDao extends JpaRepository<Software, Long> {
    List<Software> findAllByComputerId(Long id);
}
