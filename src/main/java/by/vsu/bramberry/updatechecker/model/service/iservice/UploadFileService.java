package by.vsu.bramberry.updatechecker.model.service.iservice;

import by.vsu.bramberry.updatechecker.model.entity.UploadFile;

import java.util.List;

public interface UploadFileService {
    UploadFile getByFileName(String filename);

    UploadFile save(UploadFile uploadFile);

    List<UploadFile> getAll();

    void delete(Long id);
}
