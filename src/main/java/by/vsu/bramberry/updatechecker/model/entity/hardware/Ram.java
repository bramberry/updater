package by.vsu.bramberry.updatechecker.model.entity.hardware;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;


@NoArgsConstructor
@Data
class Ram implements Serializable {

    private String volume;
    private String model;
    private String technicalSpeed;
    private String serialNumber;

}
