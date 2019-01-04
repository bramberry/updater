package by.vsu.bramberry.updateChecker.model.service;

import by.vsu.bramberry.updateChecker.model.dao.RamDao;
import by.vsu.bramberry.updateChecker.model.entity.hardware.Ram;
import by.vsu.bramberry.updateChecker.model.service.iservice.RamService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class RamServiceImpl implements RamService {
    private static final Logger logger = LoggerFactory.getLogger(RamServiceImpl.class);
    private final RamDao ramDao;

    @Autowired
    public RamServiceImpl(RamDao ramDao) {
        this.ramDao = ramDao;
    }

    @Override
    public Ram save(Ram ram) {
        return ramDao.save(ram);
    }

    @Override
    public Ram findOne(Long id) {
        return ramDao.findById(id).orElseThrow(NoSuchElementException::new);
    }

    @Override
    public boolean exists(Long id) {
        return ramDao.existsById(id);
    }

    @Override
    public List<Ram> findAll() {
        return ramDao.findAll();
    }

    @Override
    public long count() {
        return ramDao.count();
    }

    @Override
    public void delete(Long id) {
        ramDao.deleteById(id);
    }

    @Override
    public void delete(Ram ram) {
        ramDao.delete(ram);
    }

    @Override
    public void deleteAll() {
        ramDao.deleteAll();
    }
}
