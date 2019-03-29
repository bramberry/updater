package by.vsu.bramberry.updatechecker.model.dao;

import by.vsu.bramberry.updatechecker.model.entity.path.Path;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PathDao extends MongoRepository<Path, Long> {
}
