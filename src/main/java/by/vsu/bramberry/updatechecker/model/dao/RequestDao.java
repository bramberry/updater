package by.vsu.bramberry.updatechecker.model.dao;

import by.vsu.bramberry.updatechecker.model.entity.Request;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface RequestDao extends MongoRepository<Request, Long> {

    List<Request> findByUserId(Long id);
}
