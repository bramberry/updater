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
class Monitor implements Serializable {

    private String diagonal;
    private String model;
}
