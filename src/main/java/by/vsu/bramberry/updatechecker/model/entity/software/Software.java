package by.vsu.bramberry.updatechecker.model.entity.software;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@NoArgsConstructor
@Data
public class Software implements Serializable {
    private String name;
    private String currentVersion;
    private String previousVersion;
    private Boolean actualVersion;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy.MM.dd HH:mm")
    private Date installDate;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy.MM.dd HH:mm")
    private Date updateDate;
}
