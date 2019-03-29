package by.vsu.bramberry.updatechecker.model.entity.hardware;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@NoArgsConstructor
@Getter
@Setter
@ToString
class Processor implements Serializable {

    private Integer amountOfCores;
    private String cpu;
    private Boolean virtualizationFirmwareEnabled;
}
