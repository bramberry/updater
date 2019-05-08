package by.vsu.bramberry.updatechecker.model.entity.hardware;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
@Data
class Monitor implements Serializable {

    private String diagonal;
    private String model;
}
