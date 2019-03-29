package by.vsu.bramberry.updatechecker.model.service.iservice;

import by.vsu.bramberry.updatechecker.model.entity.Computer;

import java.util.List;


public interface ComputerService {
    Computer save(Computer computer);

    Computer findOne(Long id);

    boolean exists(Long id);

    List<Computer> findAll();

    List<Computer> findAllByAudienceNumber(String audienceNumber);

    long count();

    void delete(Long id);

    void delete(Computer computer);

    void deleteAll();

    void update(Computer computer);

    Computer findByIp(String ip);
}
