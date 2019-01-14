package by.vsu.bramberry.updateChecker.model.service;

import by.vsu.bramberry.updateChecker.model.dao.PathDao;
import by.vsu.bramberry.updateChecker.model.entity.path.Path;
import by.vsu.bramberry.updateChecker.model.service.iservice.PathService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class PathServiceImpl implements PathService {

    private final PathDao dao;


    @Override
    public Path save(Path path) {
        return dao.save(path);
    }

    @Override
    public Path findOne(Long id) {
        return dao.findById(id).orElse(null);
    }

    @Override
    public boolean exists(Long id) {
        return dao.existsById(id);
    }

    @Override
    public List<Path> findAll() {
        return dao.findAll();
    }

    @Override
    public long count() {
        return dao.count();
    }

    @Override
    public void delete(Long id) {
        dao.deleteById(id);
    }

    @Override
    public void delete(Path path) {
        dao.delete(path);
    }

    @Override
    public void deleteAll() {
        dao.deleteAll();
    }
}
