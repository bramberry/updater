package by.vsu.bramberry.updateChecker.model.service.iservice;

import by.vsu.bramberry.updateChecker.model.entity.hardware.Monitor;

import java.util.List;

public interface MonitorService {
    Monitor save(Monitor monitor);

    Monitor findOne(Long id);

    boolean exists(Long id);

    List<Monitor> findAll();

    long count();

    void delete(Long id);

    void delete(Monitor monitor);

    void deleteAll();
}
