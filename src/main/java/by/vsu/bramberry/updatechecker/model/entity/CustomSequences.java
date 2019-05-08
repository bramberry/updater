package by.vsu.bramberry.updatechecker.model.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "custom_sequences")
@Data
public class CustomSequences {
    @Id
    private String id;
    private Long seq;
}