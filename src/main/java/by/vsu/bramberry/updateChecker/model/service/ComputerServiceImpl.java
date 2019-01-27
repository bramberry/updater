package by.vsu.bramberry.updateChecker.model.service;

import by.vsu.bramberry.updateChecker.model.dao.ComputerDao;
import by.vsu.bramberry.updateChecker.model.entity.Computer;
import by.vsu.bramberry.updateChecker.model.entity.hardware.*;
import by.vsu.bramberry.updateChecker.model.entity.software.Software;
import by.vsu.bramberry.updateChecker.model.service.iservice.*;
import by.vsu.bramberry.updateChecker.model.service.software.UpdateServiceImpl;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@AllArgsConstructor
@Slf4j
public class ComputerServiceImpl implements ComputerService {
    private final ComputerDao computerDao;
    private final HardwareService hardwareService;
    private final ProcessorService processorService;
    private final SoftwareService softwareService;
    private final RamService ramService;
    private final HddService hddService;
    private final MonitorService monitorService;

    @Override
    public Computer save(Computer computer) {
        return computerDao.save(computer);
    }

    @Override
    public Computer findOne(Long id) {
        Computer computer = computerDao.getOne(id);
        return getComputer(computer);
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
        Computer oldVersion = getComputer(findByIp(computer.getIp()));
        Hardware oldHardware = oldVersion.getHardware();

        //Если версия программы изменилась, записываем старую и устанавливаем дату обновления
        if (oldVersion.getSoftwareSet() != null) {
            UpdateServiceImpl.updateSoftware(computer.getSoftwareSet(), oldVersion.getSoftwareSet());
            for (Software s : oldVersion.getSoftwareSet()) {
                softwareService.delete(s.getId());
            }
        }
        for (Software software : computer.getSoftwareSet()) {
            software.setComputer(oldVersion);
            softwareService.save(software);
        }

        if (oldHardware != null) {
            if (oldHardware.getId() != null) {
                Set<Hdd> hdds = oldHardware.getHddSet();
                Set<Ram> rams = oldHardware.getRamSet();
                for (Hdd h : hdds) {
                    if (h.getHardware().getId().equals(oldHardware.getId()))
                        hddService.delete(h.getId());
                }
                for (Ram h : rams) {
                    if (h.getHardware().getId().equals(oldHardware.getId()))
                        ramService.delete(h.getId());
                }
                hardwareService.delete(oldHardware.getId());
                processorService.delete(oldHardware.getProcessor().getId());
                monitorService.delete(oldHardware.getMonitor().getId());
            }
        }
        //создаем запись процессора в таблице
        Processor processor = computer.getHardware().getProcessor();
        processor = processorService.save(processor);
        //Монитор
        Monitor monitor = computer.getHardware().getMonitor();
        monitor = monitorService.save(monitor);

        //создаем железо
        oldHardware = new Hardware();
        oldHardware.setProcessor(processor);
        oldHardware.setMonitor(monitor);
        oldHardware.setComputer(oldVersion);
        oldHardware = hardwareService.save(oldHardware);

        //создаем записи в таблицах оперативки и жестких дисков
        Set<Hdd> hddSet = computer.getHardware().getHddSet();
        Set<Ram> ramSet = computer.getHardware().getRamSet();
        for (Hdd hdd : hddSet) {
            hdd.setHardware(oldHardware);
            hddService.save(hdd);
        }

        for (Ram ram : ramSet) {
            ram.setHardware(oldHardware);
            ramService.save(ram);
        }
    }

    @Override
    public Computer findByIp(String ip) {
        return computerDao.findByIp(ip);
    }

    private Computer getComputer(Computer computer) {
        Set<Software> softwareList = new HashSet<>(softwareService.findByComputerId(computer.getId()));
        computer.setSoftwareSet(softwareList);

        Hardware hardware = hardwareService.findByComputer(computer);
        if (hardware != null) {
            if (hardware.getId() != null) {
                hardware.setProcessor(processorService.findOne(hardware.getProcessor().getId()));
                hardware.setMonitor(monitorService.findOne(hardware.getMonitor().getId()));
            }
            hardware.setRamSet(ramService.findByHardwareId(hardware.getId()));
            hardware.setHddSet(hddService.findByHardwareId(hardware.getId()));
            computer.setHardware(hardware);
        }
        return computer;
    }
}
