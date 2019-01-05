package by.vsu.bramberry.updateChecker.model.service;

import by.vsu.bramberry.updateChecker.model.dao.SoftwareDao;
import by.vsu.bramberry.updateChecker.model.entity.software.Software;
import by.vsu.bramberry.updateChecker.model.service.iservice.SoftwareService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class SoftwareServiceImpl implements SoftwareService {

    private static final Logger logger = LoggerFactory.getLogger(SoftwareServiceImpl.class);
    private final SoftwareDao softwareDao;

    @Autowired
    public SoftwareServiceImpl(SoftwareDao softwareDao) {
        this.softwareDao = softwareDao;
    }

    @Override
    public Software save(Software software) {
        return softwareDao.save(software);
    }

    @Override
    public Software findOne(Long id) {
        return softwareDao.findById(id).orElse(null);
    }

    @Override
    public boolean exists(Long id) {
        return softwareDao.existsById(id);
    }

    @Override
    public List<Software> findAll() {
        return softwareDao.findAll();
    }

    @Override
    public long count() {
        return softwareDao.count();
    }

    @Override
    public void delete(Long id) {
        softwareDao.deleteById(id);
    }

    @Override
    public void delete(Software software) {
        softwareDao.delete(software);
    }

    @Override
    public void deleteAll() {
        softwareDao.deleteAll();
    }
}
