package by.vsu.bramberry.updateChecker.model.service;

import by.vsu.bramberry.updateChecker.model.entity.software.Software;

import java.util.Date;
import java.util.Set;


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

    /*public static void updateHdd(Set<Hdd> newSet, Set<Hdd> oldSet) {
        for (Hdd hdd : newSet) {
            if (!oldSet.isEmpty()) {
                for (Hdd h : oldSet) {
                    if (hdd.getDiskName().equals(h.getDiskName())) {
                        hdd.setFragmentation(h.getFragmentation());
                        hdd.setModel(h.getModel());
                    }
                }
            }
        }
    }*/

    /*public static void updateRam(Set<Ram> newSet, Set<Ram> oldSet) {
        for (Ram ram : newSet) {
            if (!oldSet.isEmpty()) {
                for (Ram r : oldSet) {
                    if (ram.getSerialNumber().equals(r.getSerialNumber())) {
                        ram.setModel(r.getModel());
                        ram.setTechnicalSpeed(r.getTechnicalSpeed());
                        ram.setVolume(r.getVolume());

                    }
                }
            }
        }
    }*/

    /*public static void updateProcessor(Processor _new, Processor old) {

        if (!(_new.getAmountOfCores().equals(old.getAmountOfCores()))) {
            old.setAmountOfCores(_new.getAmountOfCores());
        }
        if (!(_new.getCpu().equals(old.getCpu()))) {
            old.setCpu(_new.getCpu());
        }

    }*/
}
