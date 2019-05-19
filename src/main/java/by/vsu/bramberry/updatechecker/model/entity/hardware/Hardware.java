package by.vsu.bramberry.updatechecker.model.entity.hardware;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@NoArgsConstructor
@Data
public class Hardware implements Serializable {

    private Set<Hdd> hddSet = new HashSet<>();
    private Set<Ram> ramSet = new HashSet<>();
    private List<NetworkAdapter> networkAdapters = new ArrayList<>();
    private Processor processor;
    private Monitor monitor;
}
