package by.vsu.bramberry.updateChecker.model.service.iservice;

import by.vsu.bramberry.updateChecker.model.entity.UploadFile;

public interface UploadFileService {
    UploadFile getByFileName(String filename);

    UploadFile save(UploadFile uploadFile);
}
