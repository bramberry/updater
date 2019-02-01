package by.vsu.bramberry.updateChecker.model.entity.path;


import lombok.*;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;


@NoArgsConstructor
@EqualsAndHashCode
@ToString
@Getter
@Setter
public class Path implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String path;

}
