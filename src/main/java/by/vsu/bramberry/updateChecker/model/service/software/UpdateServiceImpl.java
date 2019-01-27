package by.vsu.bramberry.updateChecker.model.service.software;

import by.vsu.bramberry.updateChecker.model.entity.software.Software;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;
import java.util.Set;

@Slf4j
public class UpdateServiceImpl {
    public static void updateSoftware(Set<Software> newSet, Set<Software> oldSet) {
        for (Software software : newSet) {
            if (!oldSet.isEmpty()) {
                for (Software s : oldSet) {
                    if (software.getName().equals(s.getName())) {
                        if (!software.getCurrentVersion().equals(s.getCurrentVersion())) {
                            software.setPreviousVersion(s.getCurrentVersion());
                            software.setUpdateDate(new Date());
                        }
                    }
                }
            }
        }
    }
}
