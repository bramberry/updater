package by.vsu.bramberry.updateChecker.model.entity.hardware;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "processor")
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@Getter
@Setter
public class Processor implements Serializable {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Integer amountOfCores;
    private String cpu;
    @JsonIgnore
    @OneToOne(fetch = FetchType.LAZY, mappedBy = "processor")
    private Hardware hardware;
    private Boolean virtualizationFirmwareEnabled;
}
