package by.vsu.bramberry.updateChecker.model.service;

import by.vsu.bramberry.updateChecker.model.dao.SoftwareDao;
import by.vsu.bramberry.updateChecker.model.entity.software.Software;
import by.vsu.bramberry.updateChecker.model.service.iservice.SoftwareService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class SoftwareServiceImpl implements SoftwareService {

    private final SoftwareDao softwareDao;

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
    public List<Software> findByComputerId(Long id) {
        return softwareDao.findAllByComputerId(id);
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
