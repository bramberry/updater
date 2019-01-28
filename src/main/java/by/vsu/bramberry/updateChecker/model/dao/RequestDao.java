package by.vsu.bramberry.updateChecker.model.dao;

import by.vsu.bramberry.updateChecker.model.entity.Request;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RequestDao extends JpaRepository<Request, Long> {
}
