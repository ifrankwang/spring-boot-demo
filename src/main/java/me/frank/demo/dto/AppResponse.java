package me.frank.demo.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author 王明哲
 */
@Data
public class AppResponse<T> {
    @ApiModelProperty(value = "成功标志", example = "true")
    private boolean success;
    @ApiModelProperty(value = "错误信息", example = "success")
    private String message;
    @ApiModelProperty("返回数据")
    private T data;

    public AppResponse() {
    }

    private AppResponse(boolean success, String message, T data) {
        this.success = success;
        this.message = message;
        this.data = data;
    }

    public static <T> AppResponse<T> success(T data) {
        return new AppResponse<>(true, "success", data);
    }

    public static <T> AppResponse<T> failed(String message) {
        return new AppResponse<>(false, message, null);
    }
}
