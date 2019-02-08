package by.vsu.bramberry.updateChecker.model.service.iservice;

import by.vsu.bramberry.updateChecker.model.dao.RequestDao;
import by.vsu.bramberry.updateChecker.model.entity.Request;
import by.vsu.bramberry.updateChecker.model.service.SequenceGeneratorService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class RequestServiceImpl implements RequestService {
    private final RequestDao dao;
    private final SequenceGeneratorService sequenceGeneratorService;

    @Override
    public Request save(Request request) {
        request.setId(sequenceGeneratorService.getNextSequence(Request.SEQUENCE_NAME));
        return dao.save(request);
    }

    @Override
    public void delete(Long id) {
        dao.deleteById(id);
    }

    @Override
    public List<Request> findAll() {
        return dao.findAll();
    }
}
