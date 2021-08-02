package tech.dongkaiyang.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * @ClassName RecordVo
 * @Description
 * @Author zyx
 * @Date 2021-08-01 15:20
 * @Blog www.winkelblog.top
 */

@Data
@NoArgsConstructor
@AllArgsConstructor

public class RecordVo {

//    @JsonProperty("stuCard")
//    private String stuCard;
    @JsonProperty("teaCard")
    private String teaCard;
    @JsonProperty("startTime")
    private String startTime;
    @JsonProperty("endTime")
    private String endTime;
    @JsonProperty("location")
    private String location;

}
