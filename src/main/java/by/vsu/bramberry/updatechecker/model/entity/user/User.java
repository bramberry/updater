package by.vsu.bramberry.updatechecker.model.entity.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.Indexed;

import javax.validation.constraints.NotNull;
import java.util.Date;

@NoArgsConstructor
@Data
public class User {
    @Transient
    public static final String SEQUENCE_NAME = "users_sequence";

    @Id
    private Long id;
    @JsonProperty("username")
    @Indexed(unique = true)
    private String username;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @NotNull
    private String password;
    @JsonProperty("firstName")
    private String firstName;
    @JsonProperty("lastName")
    private String lastName;
    @JsonProperty("dateRegistered")
    private Date dateRegistered;
    private Role role;

}
