package by.vsu.bramberry.updateChecker.model.service.hardware;

import by.vsu.bramberry.updateChecker.model.dao.ProcessorDao;
import by.vsu.bramberry.updateChecker.model.entity.hardware.Processor;
import by.vsu.bramberry.updateChecker.model.service.iservice.ProcessorService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class ProcessorServiceImpl implements ProcessorService {
    private final ProcessorDao processorDao;


    @Override
    public Processor save(Processor processor) {
        return processorDao.save(processor);
    }

    @Override
    public Processor findOne(Long id) {
        return processorDao.findById(id).orElse(null);
    }

    @Override
    public boolean exists(Long id) {
        return processorDao.existsById(id);
    }

    @Override
    public List<Processor> findAll() {
        return processorDao.findAll();
    }

    @Override
    public long count() {
        return processorDao.count();
    }

    @Override
    public void delete(Long id) {
        processorDao.deleteById(id);
    }

    @Override
    public void delete(Processor processor) {
        processorDao.delete((processor));
    }

    @Override
    public void deleteAll() {
        processorDao.deleteAll();
    }
}
