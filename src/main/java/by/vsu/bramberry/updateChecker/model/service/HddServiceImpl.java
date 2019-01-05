package by.vsu.bramberry.updateChecker.model.service;

import by.vsu.bramberry.updateChecker.model.dao.HddDao;
import by.vsu.bramberry.updateChecker.model.entity.hardware.Hdd;
import by.vsu.bramberry.updateChecker.model.service.iservice.HddService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class HddServiceImpl implements HddService {

    private static final Logger logger = LoggerFactory.getLogger(HddServiceImpl.class);
    private final HddDao hddDao;

    @Autowired
    public HddServiceImpl(HddDao hddDao) {
        this.hddDao = hddDao;
    }

    @Override
    public Hdd save(Hdd hdd) {
        return hddDao.save(hdd);
    }

    @Override
    public Hdd findOne(Long id) {
        return hddDao.findById(id).orElse(null);
    }

    @Override
    public boolean exists(Long id) {
        return hddDao.existsById(id);
    }

    @Override
    public List<Hdd> findAll() {
        return hddDao.findAll();
    }

    @Override
    public long count() {
        return hddDao.count();
    }

    @Override
    public void delete(Long id) {
        hddDao.deleteById(id);
    }

    @Override
    public void delete(Hdd hdd) {
        hddDao.delete(hdd);
    }

    @Override
    public void deleteAll() {
        hddDao.deleteAll();
    }
}
