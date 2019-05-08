package by.vsu.bramberry.updatechecker.model.entity.hardware;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
@Data
class Hdd implements Serializable {
    private String diskName;
    private String model;
    private String fragmentation;

}
