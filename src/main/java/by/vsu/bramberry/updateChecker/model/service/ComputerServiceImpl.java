package by.vsu.bramberry.updateChecker.model.service;

import by.vsu.bramberry.updateChecker.model.dao.ComputerDao;
import by.vsu.bramberry.updateChecker.model.entity.Computer;
import by.vsu.bramberry.updateChecker.model.entity.hardware.*;
import by.vsu.bramberry.updateChecker.model.entity.software.Software;
import by.vsu.bramberry.updateChecker.model.service.iservice.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ComputerServiceImpl implements ComputerService {
    private static final Logger logger = LoggerFactory.getLogger(ComputerServiceImpl.class);
    private final ComputerDao computerDao;
    private final HardwareService hardwareService;
    private final ProcessorService processorService;
    private final SoftwareService softwareService;
    private final RamService ramService;
    private final HddService hddService;
    private final MonitorService monitorService;


    @Autowired
    public ComputerServiceImpl(ComputerDao computerDao, HardwareService hardwareService, ProcessorService processorService, SoftwareService softwareService, RamService ramService, HddService hddService, MonitorService monitorService) {
        this.computerDao = computerDao;
        this.hardwareService = hardwareService;
        this.processorService = processorService;
        this.softwareService = softwareService;
        this.ramService = ramService;
        this.hddService = hddService;
        this.monitorService = monitorService;
    }

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
    public List<Computer> findAll(Pageable pageable) {
        Page<Computer> computers = computerDao.findAll(pageable);
        computers.forEach(computer -> computer = getComputer(computer));
        return computers.stream().collect(Collectors.toList());
    }

    @Override
    public List<Computer> findAllByAudienceNumber(String audienceNumber, Pageable pageable) {
        return computerDao.findAllByAudienceNumber(audienceNumber, pageable);
    }

    @Override
    public long count() {
        return computerDao.count();
    }

    @Override
    public void delete(Long id) {
        Computer oldVersion = findOne(id);
        Hardware oldHardware = hardwareService.findByComputer(oldVersion);

        //Если версия программы изменилась, записываем старую и устанавливаем дату обновления
        if (oldVersion.getSoftwareSet() != null) {
            for (Software s : oldVersion.getSoftwareSet()) {
                softwareService.delete(s.getId());
            }
        }
        if (oldHardware != null) {
            if (oldHardware.getId() != null) {
                List<Hdd> hdds = hddService.findAll();
                List<Ram> rams = ramService.findAll();
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
        Hardware oldHardware = hardwareService.findByComputer(oldVersion);

        //Если версия программы изменилась, записываем старую и устанавливаем дату обновления
        if (oldVersion.getSoftwareSet() != null) {
            UpdateServiceImpl.updateSoftware(computer.getSoftwareSet(), oldVersion.getSoftwareSet());
            for (Software s : oldVersion.getSoftwareSet()) {
                softwareService.delete(s.getId());
            }
        }
        for (Software software : computer.getSoftwareSet()) {
            software.setComputer(oldVersion);
            software = softwareService.save(software);
        }

        if (oldHardware != null) {
            if (oldHardware.getId() != null) {
                List<Hdd> hdds = hddService.findAll();
                List<Ram> rams = ramService.findAll();
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
            hdd = hddService.save(hdd);
        }

        for (Ram ram : ramSet) {
            ram.setHardware(oldHardware);
            ram = ramService.save(ram);
        }
    }

    @Override
    public Computer findByIp(String ip) {
        return computerDao.findByIp(ip);
    }

    private Computer getComputer(Computer computer) {
        computer.setSoftwareSet(new HashSet<>());
        for (Software software : softwareService.findAll()) {
            if (software.getComputer() == computer)
                computer.getSoftwareSet().add(software);
        }
        Hardware hardware = hardwareService.findByComputer(computer);
        Set<Hdd> hddSet = new HashSet<>();
        Set<Ram> ramSet = new HashSet<>();
        if (hardware != null) {
            if (hardware.getId() != null) {
                for (Hdd h : hddService.findAll()) {
                    if (h.getHardware().getId().equals(hardware.getId()))
                        hddSet.add(h);
                }

                for (Ram ram : ramService.findAll()) {
                    if (ram.getHardware().getId().equals(hardware.getId())) {
                        ramSet.add(ram);
                    }
                }
                hardware.setProcessor(processorService.findOne(hardware.getProcessor().getId()));
                hardware.setMonitor(monitorService.findOne(hardware.getMonitor().getId()));
            }
            hardware.setRamSet(ramSet);
            hardware.setHddSet(hddSet);
            computer.setHardware(hardware);
        }
        return computer;
    }
}
