package by.vsu.bramberry.updateChecker.model.service;

import by.vsu.bramberry.updateChecker.model.dao.HddDao;
import by.vsu.bramberry.updateChecker.model.entity.hardware.Hdd;
import by.vsu.bramberry.updateChecker.model.service.iservice.HddService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
@AllArgsConstructor
@Slf4j
public class HddServiceImpl implements HddService {

    private final HddDao hddDao;



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
    public Set<Hdd> findByHardwareId(Long id) {
        return findByHardwareId(id);
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
