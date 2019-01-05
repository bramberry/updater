package by.vsu.bramberry.updateChecker.model.dao;

import by.vsu.bramberry.updateChecker.model.entity.Computer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ComputerDao extends JpaRepository<Computer, Long> {
    Computer findByIp(String ip);

    Page<Computer> findAll(Pageable pageable);

    List<Computer> findAllByAudienceNumber(String audienceNumber);
}
