package by.vsu.bramberry.updateChecker.model.entity;

import by.vsu.bramberry.updateChecker.model.entity.user.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "request")
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@Getter
@Setter
public class Request {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String audienceNumber;
    private String description;
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}
