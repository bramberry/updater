package by.vsu.bramberry.updateChecker.model.service.iservice;

import by.vsu.bramberry.updateChecker.model.entity.Computer;
import by.vsu.bramberry.updateChecker.model.entity.hardware.Hardware;

import java.util.List;


public interface HardwareService {
    Hardware save(Hardware computer);

    Hardware findOne(Long id);

    boolean exists(Long id);

    List<Hardware> findAll();

    long count();

    void delete(Long id);

    void delete(Hardware computer);

    void deleteAll();

    Hardware findByComputer(Computer computer);
}
