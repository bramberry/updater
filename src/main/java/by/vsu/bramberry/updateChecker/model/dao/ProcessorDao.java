package by.vsu.bramberry.updateChecker.model.dao;

import by.vsu.bramberry.updateChecker.model.entity.hardware.Processor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProcessorDao extends JpaRepository<Processor, Long> {
}
