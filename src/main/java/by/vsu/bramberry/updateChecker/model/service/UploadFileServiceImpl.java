package by.vsu.bramberry.updateChecker.model.service;

import by.vsu.bramberry.updateChecker.model.dao.UploadFileDao;
import by.vsu.bramberry.updateChecker.model.entity.UploadFile;
import by.vsu.bramberry.updateChecker.model.service.iservice.UploadFileService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UploadFileServiceImpl implements UploadFileService {
    private final UploadFileDao dao;

    @Override
    public UploadFile getByFileName(String filename) {
        return dao.findByFileName(filename);
    }

    @Override
    public UploadFile save(UploadFile uploadFile) {
        return dao.save(uploadFile);
    }
}
