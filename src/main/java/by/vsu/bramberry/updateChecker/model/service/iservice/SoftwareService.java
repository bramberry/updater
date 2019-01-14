package by.vsu.bramberry.updateChecker.model.service.iservice;

import by.vsu.bramberry.updateChecker.model.entity.software.Software;

import java.util.List;


public interface SoftwareService {
    Software save(Software computer);

    Software findOne(Long id);

    boolean exists(Long id);

    List<Software> findAll();

    List<Software> findByComputerId(Long id);

    long count();

    void delete(Long id);

    void delete(Software computer);

    void deleteAll();
}
