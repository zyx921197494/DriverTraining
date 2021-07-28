package tech.dongkaiyang.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class Record {

    private int id;
    private int stuId;
    private int teaId;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String location;
    private int status;

}
