package by.vsu.bramberry.updateChecker.model.dao;

import by.vsu.bramberry.updateChecker.model.entity.hardware.Hdd;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HddDao extends JpaRepository<Hdd, Long> {

    void deleteAllByHardware_Id(Long id);
}
