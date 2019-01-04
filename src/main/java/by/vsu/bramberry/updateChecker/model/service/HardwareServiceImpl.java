package by.vsu.bramberry.updateChecker.model.service;

import by.vsu.bramberry.updateChecker.model.dao.HardwareDao;
import by.vsu.bramberry.updateChecker.model.entity.Computer;
import by.vsu.bramberry.updateChecker.model.entity.hardware.Hardware;
import by.vsu.bramberry.updateChecker.model.service.iservice.HardwareService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class HardwareServiceImpl implements HardwareService {
    private static final Logger logger = LoggerFactory.getLogger(HardwareServiceImpl.class);
    private final HardwareDao hardwareDao;

    @Autowired
    public HardwareServiceImpl(HardwareDao hardwareDao) {
        this.hardwareDao = hardwareDao;
    }

    @Override
    public Hardware save(Hardware hardware) {
        return hardwareDao.save(hardware);
    }

    @Override
    public Hardware findOne(Long id) {
        return hardwareDao.findById(id).orElseThrow(NoSuchElementException::new);
    }

    @Override
    public Hardware findByComputer(Computer computer) {
        return hardwareDao.findByComputer(computer);
    }

    @Override
    public boolean exists(Long id) {
        return hardwareDao.existsById(id);
    }

    @Override
    public List<Hardware> findAll() {
        return hardwareDao.findAll();
    }

    @Override
    public long count() {
        return hardwareDao.count();
    }

    @Override
    public void delete(Long id) {
        hardwareDao.deleteById(id);
    }

    @Override
    public void delete(Hardware hardware) {
        hardwareDao.delete(hardware);
    }

    @Override
    public void deleteAll() {
        hardwareDao.deleteAll();
    }
}
