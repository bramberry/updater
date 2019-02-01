package by.vsu.bramberry.updateChecker.model.entity.software;

import by.vsu.bramberry.updateChecker.model.entity.Computer;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class Software implements Serializable {
    private String name;
    private String currentVersion;
    private String previousVersion;
    private Boolean actualVersion;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy.MM.dd HH:mm")
    private Date installDate;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy.MM.dd HH:mm")
    private Date updateDate;
    private Computer computer;

}
