package by.vsu.bramberry.updateChecker.model.entity.hardware;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class Processor implements Serializable {

    private Integer amountOfCores;
    private String cpu;
    private Boolean virtualizationFirmwareEnabled;
}
