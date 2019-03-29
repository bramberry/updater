package by.vsu.bramberry.updatechecker.model.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "custom_sequences")
@Getter
@Setter
public class CustomSequences {
    @Id
    private String id;
    private Long seq;
}