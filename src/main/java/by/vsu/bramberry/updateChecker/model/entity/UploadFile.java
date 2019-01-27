package by.vsu.bramberry.updateChecker.model.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "computer")
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@Getter
@Setter
public class UploadFile {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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