package by.vsu.bramberry.updatechecker.model.entity.hardware;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
@Data
class Processor implements Serializable {

    private Integer amountOfCores;
    private String cpu;
    private Boolean virtualizationFirmwareEnabled;
}
