package by.vsu.bramberry.updateChecker.model.service.iservice;

import by.vsu.bramberry.updateChecker.model.entity.Computer;
import org.springframework.data.domain.Pageable;

import java.util.List;


public interface ComputerService {
    Computer save(Computer computer);

    Computer findOne(Long id);

    boolean exists(Long id);

    List<Computer> findAll(Pageable pageable);

    List<Computer> findAll();

    List<Computer> findAllByAudienceNumber(String audienceNumber);

    long count();

    void delete(Long id);

    void delete(Computer computer);

    void deleteAll();

    void update(Computer computer);

    Computer findByIp(String ip);
}
