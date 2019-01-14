package by.vsu.bramberry.updateChecker.model.entity.path;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "path")
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@Getter
@Setter
public class Path implements Serializable {
    @JsonIgnore
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String path;

}
