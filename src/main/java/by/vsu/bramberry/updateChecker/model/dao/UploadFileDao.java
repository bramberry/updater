package by.vsu.bramberry.updateChecker.model.dao;

import by.vsu.bramberry.updateChecker.model.entity.UploadFile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UploadFileDao extends JpaRepository<UploadFile, Long> {
    UploadFile findByFileName(String filename);
}
