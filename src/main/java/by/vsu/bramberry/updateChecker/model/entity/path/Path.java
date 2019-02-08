package by.vsu.bramberry.updateChecker.model.entity.path;


import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;

import java.io.Serializable;


@NoArgsConstructor
@EqualsAndHashCode
@ToString
@Getter
@Setter
public class Path implements Serializable {

    @Transient
    public static final String SEQUENCE_NAME = "path_sequence";
    @Id
    private Long id;
    private String path;

}
