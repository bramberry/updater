package by.vsu.bramberry.updatechecker.model.service;

import by.vsu.bramberry.updatechecker.model.dao.ComputerDao;
import by.vsu.bramberry.updatechecker.model.entity.Computer;
import by.vsu.bramberry.updatechecker.model.entity.software.Software;
import by.vsu.bramberry.updatechecker.model.service.iservice.ComputerService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Set;

@Service
@AllArgsConstructor
@Slf4j
public class ComputerServiceImpl implements ComputerService {
    private final ComputerDao computerDao;
    private final SequenceGeneratorService sequenceGeneratorService;

    @Override
    public Computer save(Computer computer) {
        computer.setId(sequenceGeneratorService.getNextSequence(Computer.SEQUENCE_NAME));
        return computerDao.save(computer);
    }

    @Override
    public Computer findOne(Long id) {
        return computerDao.findById(id).orElse(null);
    }

    @Override
    public boolean exists(Long id) {
        return computerDao.existsById(id);
    }

    @Override
    public List<Computer> findAll() {
        return computerDao.findAll();
    }

    @Override
    public List<Computer> findAllByAudienceNumber(String audienceNumber) {
        return computerDao.findAllByAudienceNumber(audienceNumber);
    }

    @Override
    public long count() {
        return computerDao.count();
    }

    @Override
    public void delete(Long id) {
        computerDao.deleteById(id);
    }

    @Override
    public void delete(Computer computer) {
        computerDao.delete(computer);
    }

    @Override
    public void deleteAll() {
        computerDao.deleteAll();
    }

    @Override
    public void update(Computer computer) {
        Computer oldVersion = findByIp(computer.getIp());

        if (computer.getSoftwareSet() == null) {
            computer.setSoftwareSet(oldVersion.getSoftwareSet());
        }
        //Если версия программы изменилась, записываем старую и устанавливаем дату обновления
        if (oldVersion.getSoftwareSet() != null) {
            updateSoftware(computer.getSoftwareSet(), oldVersion.getSoftwareSet());
        }
        if (computer.getIp() == null) {
            computer.setIp(oldVersion.getIp());
        }
        if (computer.getId() == null) {
            computer.setId(oldVersion.getId());
        }
        if (computer.getDescription() == null) {
            computer.setDescription(oldVersion.getDescription());
        }
        if (computer.getAudienceNumber() == null) {
            computer.setAudienceNumber(oldVersion.getAudienceNumber());
        }
        if (computer.getMac() == null) {
            computer.setMac(oldVersion.getMac());
        }
        if (computer.getSystemUnitInvNumber() == null) {
            computer.setSystemUnitInvNumber(oldVersion.getSystemUnitInvNumber());
        }
        if (computer.getMonitorInvNumber() == null) {
            computer.setMonitorInvNumber(oldVersion.getMonitorInvNumber());
        }
        if (computer.getHardware() == null) {
            computer.setHardware(oldVersion.getHardware());
        }
        computerDao.deleteById(computer.getId());
        computerDao.save(computer);
    }

    @Override
    public Computer findByIp(String ip) {
        return computerDao.findByIp(ip);
    }

    private void updateSoftware(Set<Software> newSet, Set<Software> oldSet) {
        if (newSet == null || oldSet == null) {
            return;
        }
        for (Software software : newSet) {
            for (Software s : oldSet) {
                if (!software.getName().equals(s.getName())) {
                    continue;
                }
                if (!software.getCurrentVersion().equals(s.getCurrentVersion())) {
                    software.setPreviousVersion(s.getCurrentVersion());
                    software.setUpdateDate(new Date());
                }

            }
        }
    }
}
