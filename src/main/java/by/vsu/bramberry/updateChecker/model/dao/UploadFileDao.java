package by.vsu.bramberry.updateChecker.model.dao;

import by.vsu.bramberry.updateChecker.model.entity.UploadFile;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UploadFileDao extends MongoRepository<UploadFile, Long> {
    UploadFile findByFileName(String filename);
}
