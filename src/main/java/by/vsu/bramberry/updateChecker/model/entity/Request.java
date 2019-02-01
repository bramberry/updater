package by.vsu.bramberry.updateChecker.model.entity;

import by.vsu.bramberry.updateChecker.model.entity.user.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Id;
import javax.persistence.Transient;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class Request {
    @Transient
    public static final String SEQUENCE_NAME = "request_sequence";
    @Id
    private Long id;
    private String audienceNumber;
    private String description;
    private User user;

}
