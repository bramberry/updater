package by.vsu.bramberry.updateChecker.model.service.iservice;

import by.vsu.bramberry.updateChecker.model.entity.hardware.Processor;

import java.util.List;

public interface ProcessorService {
    Processor save(Processor processor);

    Processor findOne(Long id);

    boolean exists(Long id);

    List<Processor> findAll();

    long count();

    void delete(Long id);

    void delete(Processor processor);

    void deleteAll();
}
