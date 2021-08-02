package tech.dongkaiyang.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Record implements Serializable {


    private int id;
    private String stuCard;
    private String teaCard;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String location;
    private int status;

}
