package by.vsu.bramberry.updatechecker.model.entity.path;


import lombok.*;
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
