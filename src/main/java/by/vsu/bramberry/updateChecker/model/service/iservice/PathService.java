package by.vsu.bramberry.updateChecker.model.service.iservice;

import by.vsu.bramberry.updateChecker.model.entity.path.Path;

import java.util.List;

public interface PathService {

    Path save(Path path);

    Path findOne(Long id);

    boolean exists(Long id);

    List<Path> findAll();

    long count();

    void delete(Long id);

    void delete(Path path);

    void deleteAll();
}
