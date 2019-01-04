package by.vsu.bramberry.updateChecker.model.service;

import by.vsu.bramberry.updateChecker.model.dao.MonitorDao;
import by.vsu.bramberry.updateChecker.model.entity.hardware.Monitor;
import by.vsu.bramberry.updateChecker.model.service.iservice.MonitorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class MonitorServiceImpl implements MonitorService {
    private MonitorDao monitorDao;

    @Autowired
    public MonitorServiceImpl(MonitorDao monitorDao) {
        this.monitorDao = monitorDao;
    }

    @Override
    public Monitor save(Monitor monitor) {
        return monitorDao.save(monitor);
    }

    @Override
    public Monitor findOne(Long id) {
        return monitorDao.findById(id).orElseThrow(NoSuchElementException::new);
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
