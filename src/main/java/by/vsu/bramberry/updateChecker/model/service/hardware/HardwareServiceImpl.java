package by.vsu.bramberry.updateChecker.model.service.hardware;

import by.vsu.bramberry.updateChecker.model.dao.HardwareDao;
import by.vsu.bramberry.updateChecker.model.entity.Computer;
import by.vsu.bramberry.updateChecker.model.entity.hardware.Hardware;
import by.vsu.bramberry.updateChecker.model.service.iservice.HardwareService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class HardwareServiceImpl implements HardwareService {

    private final HardwareDao hardwareDao;


    @Override
    public Hardware save(Hardware hardware) {
        return hardwareDao.save(hardware);
    }

    @Override
    public Hardware findOne(Long id) {
        return hardwareDao.findById(id).orElse(null);
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
