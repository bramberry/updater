package by.vsu.bramberry.updatechecker.model.service;

import by.vsu.bramberry.updatechecker.model.dao.UploadFileDao;
import by.vsu.bramberry.updatechecker.model.entity.UploadFile;
import by.vsu.bramberry.updatechecker.model.service.iservice.UploadFileService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UploadFileServiceImpl implements UploadFileService {
    private final UploadFileDao dao;
    private final SequenceGeneratorService sequenceGeneratorService;

    @Override
    public UploadFile getByFileName(String filename) {
        return dao.findByFileName(filename);
    }

    @Override
    public UploadFile save(UploadFile uploadFile) {
        uploadFile.setId(sequenceGeneratorService.getNextSequence(UploadFile.SEQUENCE_NAME));
        return dao.save(uploadFile);
    }

    @Override
    public List<UploadFile> getAll() {
        return dao.findAll();
    }

    @Override
    public void delete(Long id) {
        dao.deleteById(id);
    }
}
