package by.vsu.bramberry.updateChecker.model.service.iservice;

import by.vsu.bramberry.updateChecker.model.entity.UploadFile;

import java.util.List;

public interface UploadFileService {
    UploadFile getByFileName(String filename);

    UploadFile save(UploadFile uploadFile);

    List<UploadFile> getAll();

    void delete(Long id);
}
