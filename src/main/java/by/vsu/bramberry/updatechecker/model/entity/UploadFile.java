package by.vsu.bramberry.updatechecker.model.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.Indexed;


@NoArgsConstructor
@Data
public class UploadFile {
    @Transient
    public static final String SEQUENCE_NAME = "files_sequence";
    @Id
    private Long id;
    @Indexed(unique = true)
    private String fileName;
    private String fileDownloadUri;
    private String fileType;
    private long size;

    public UploadFile(String fileName, String fileDownloadUri, String fileType, long size) {
        this.fileName = fileName;
        this.fileDownloadUri = fileDownloadUri;
        this.fileType = fileType;
        this.size = size;
    }
}