package by.vsu.bramberry.updateChecker.model.entity.hardware;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;


@NoArgsConstructor
@Getter
@Setter
public class Ram implements Serializable {

    private String volume;
    private String model;
    private String technicalSpeed;
    private String serialNumber;

}
