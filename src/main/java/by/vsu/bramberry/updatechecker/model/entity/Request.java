package by.vsu.bramberry.updatechecker.model.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;


@NoArgsConstructor
@Data
@Document
public class Request {
    @Transient
    public static final String SEQUENCE_NAME = "request_sequence";
    @Id
    private Long id;
    private String audienceNumber;
    private String description;
    private String context;
    private String state;
    private Long userId;
    private String username;

}
