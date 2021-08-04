package tech.dongkaiyang.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @ClassName Result
 * @Description
 * @Author zyx
 * @Date 2021-07-31 12:45
 * @Blog www.winkelblog.top
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Result<T> implements Serializable {

    private String status;

    private String msg;

    private T data;

    public Result(String status, String msg) {
        this.status = status;
        this.msg = msg;
    }

    public static <T> Result<T> success(String msg) {
        return new Result<T>("1", msg, null);
    }

    public static <T> Result<T> success(String msg, T data) {
        return new Result<T>("1", msg, data);
    }

    public static <T> Result<T> fail(String msg) {
        return new Result<T>("0", msg, null);
    }

}
