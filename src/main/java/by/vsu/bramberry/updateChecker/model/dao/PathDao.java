package by.vsu.bramberry.updateChecker.model.dao;

import by.vsu.bramberry.updateChecker.model.entity.path.Path;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PathDao extends MongoRepository<Path, Long> {
}
