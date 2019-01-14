package by.vsu.bramberry.updateChecker.model.entity.hardware;

import by.vsu.bramberry.updateChecker.model.entity.Computer;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "hardware")
@NoArgsConstructor

@EqualsAndHashCode
@ToString
@Getter
@Setter
public class Hardware implements Serializable {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "hardware", cascade = CascadeType.REMOVE)
    private Set<Hdd> hddSet = new HashSet<>(0);
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "hardware", cascade = CascadeType.REMOVE)
    private Set<Ram> ramSet = new HashSet<>(0);
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    private Processor processor;
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    private Monitor monitor;
    @JsonIgnore
    @OneToOne(fetch = FetchType.LAZY)
    private Computer computer;
}
