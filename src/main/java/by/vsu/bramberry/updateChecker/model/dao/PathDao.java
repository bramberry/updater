package by.vsu.bramberry.updateChecker.model.dao;

import by.vsu.bramberry.updateChecker.model.entity.path.Path;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PathDao extends JpaRepository<Path, Long> {
}
