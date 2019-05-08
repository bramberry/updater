package by.vsu.bramberry.updatechecker.model.entity.hardware;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@Data
public class Hardware implements Serializable {

    private Set<Hdd> hddSet = new HashSet<>();
    private Set<Ram> ramSet = new HashSet<>();
    private Processor processor;
    private Monitor monitor;
}
