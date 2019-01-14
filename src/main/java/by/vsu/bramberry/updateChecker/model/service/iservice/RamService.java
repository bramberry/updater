package by.vsu.bramberry.updateChecker.model.service.iservice;

import by.vsu.bramberry.updateChecker.model.entity.hardware.Ram;

import java.util.List;
import java.util.Set;


public interface RamService {
    Ram save(Ram computer);

    Ram findOne(Long id);

    boolean exists(Long id);

    List<Ram> findAll();

    Set<Ram> findByHardwareId(Long id);

    long count();

    void delete(Long id);

    void delete(Ram computer);

    void deleteAll();
}
