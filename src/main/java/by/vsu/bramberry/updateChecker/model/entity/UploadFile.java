package by.vsu.bramberry.updateChecker.model.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Transient;


@NoArgsConstructor
@EqualsAndHashCode
@ToString
@Getter
@Setter
public class UploadFile {
    @Transient
    public static final String SEQUENCE_NAME = "files_sequence";
    @Id
    private Long id;
    @Column(unique = true)
    private String fileName;
    @Column(unique = true)
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