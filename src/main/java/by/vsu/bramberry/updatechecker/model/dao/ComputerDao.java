package by.vsu.bramberry.updatechecker.model.dao;

import by.vsu.bramberry.updatechecker.model.entity.Computer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ComputerDao extends MongoRepository<Computer, Long> {
    Computer findByIp(String ip);

    Page<Computer> findAll(Pageable pageable);

    List<Computer> findAllByAudienceNumber(String audienceNumber);
}
