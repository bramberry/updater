package by.vsu.bramberry.updateChecker.model.service;

import by.vsu.bramberry.updateChecker.model.dao.MonitorDao;
import by.vsu.bramberry.updateChecker.model.entity.hardware.Monitor;
import by.vsu.bramberry.updateChecker.model.service.iservice.MonitorService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class MonitorServiceImpl implements MonitorService {
    private MonitorDao monitorDao;


    @Override
    public Monitor save(Monitor monitor) {
        return monitorDao.save(monitor);
    }

    @Override
    public Monitor findOne(Long id) {
        return monitorDao.findById(id).orElse(null);
    }

    @Override
    public boolean exists(Long id) {
        return monitorDao.existsById(id);
    }

    @Override
    public List<Monitor> findAll() {
        return monitorDao.findAll();
    }

    @Override
    public long count() {
        return monitorDao.count();
    }

    @Override
    public void delete(Long id) {
        monitorDao.deleteById(id);
    }

    @Override
    public void delete(Monitor monitor) {
        monitorDao.delete(monitor);
    }

    @Override
    public void deleteAll() {
        monitorDao.deleteAll();
    }
}
