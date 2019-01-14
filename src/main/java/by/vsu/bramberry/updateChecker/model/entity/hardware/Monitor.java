package by.vsu.bramberry.updateChecker.model.entity.hardware;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "monitor")
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@Getter
@Setter
public class Monitor implements Serializable {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String diagonal;
    private String model;
    @JsonIgnore
    @OneToOne(fetch = FetchType.LAZY, mappedBy = "monitor")
    private Hardware hardware;

}
