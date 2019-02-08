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
class Hdd implements Serializable {
    private String diskName;
    private String model;
    private String fragmentation;

}
