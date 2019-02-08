package by.vsu.bramberry.updateChecker.model.entity.hardware;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class Hardware implements Serializable {

    private Set<Hdd> hddSet = new HashSet<>();
    private Set<Ram> ramSet = new HashSet<>();
    private Processor processor;
    private Monitor monitor;
}
