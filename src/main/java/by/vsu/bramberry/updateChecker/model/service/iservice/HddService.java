package by.vsu.bramberry.updateChecker.model.service.iservice;

import by.vsu.bramberry.updateChecker.model.entity.hardware.Hdd;

import java.util.List;
import java.util.Set;


public interface HddService {
    Hdd save(Hdd computer);

    Hdd findOne(Long id);

    boolean exists(Long id);

    List<Hdd> findAll();

    Set<Hdd> findByHardwareId(Long id);

    long count();

    void delete(Long id);

    void delete(Hdd computer);

    void deleteAll();
}
