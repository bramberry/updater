package by.vsu.bramberry.updateChecker.model.entity.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.persistence.*;
import java.util.Date;

@NoArgsConstructor
@EqualsAndHashCode
@ToString
@Getter
@Setter
@Data
public class User {
    @Transient
    public static final String SEQUENCE_NAME = "users_sequence";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, unique = true)
    @JsonProperty("username")
    private String username;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    @JsonProperty("firstName")
    private String firstName;
    @Column(nullable = false)
    @JsonProperty("lastName")
    private String lastName;
    @JsonProperty("dateRegistered")
    private Date dateRegistered;
    @Column(nullable = false)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Role role;

}
