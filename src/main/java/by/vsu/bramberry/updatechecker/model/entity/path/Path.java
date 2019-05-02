package by.vsu.bramberry.updatechecker.model.entity.path;


import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;


@NoArgsConstructor
@Data
@Document
public class Path implements Serializable {

    @Transient
    public static final String SEQUENCE_NAME = "path_sequence";
    @Id
    private Long id;
    private String path;

}
