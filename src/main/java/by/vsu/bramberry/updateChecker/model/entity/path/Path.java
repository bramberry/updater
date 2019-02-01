package by.vsu.bramberry.updateChecker.model.entity.path;


import lombok.*;
import org.springframework.data.annotation.Id;

import java.io.Serializable;


@NoArgsConstructor
@EqualsAndHashCode
@ToString
@Getter
@Setter
public class Path implements Serializable {

    @Id
    private Long id;
    private String path;

}
