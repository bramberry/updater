package by.vsu.bramberry.updateChecker.model.service;

import by.vsu.bramberry.updateChecker.model.dao.RamDao;
import by.vsu.bramberry.updateChecker.model.entity.hardware.Ram;
import by.vsu.bramberry.updateChecker.model.service.iservice.RamService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
@AllArgsConstructor
@Slf4j
public class RamServiceImpl implements RamService {
    private final RamDao ramDao;

    @Override
    public Ram save(Ram ram) {
        return ramDao.save(ram);
    }

    @Override
    public Ram findOne(Long id) {
        return ramDao.findById(id).orElse(null);
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
    public Set<Ram> findByHardwareId(Long id) {
        return ramDao.findAllByHardwareId(id);
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
