package study.mmbuyuserservice.common.resp;

import lombok.Data;
import study.mmbuyuserservice.common.constants.Constants;

@Data
public class ApiResult<T> {

    private int code = Constants.RESP_STATUS_OK;

    private String message;

    private T data;

    public ApiResult() {
    }

    public ApiResult(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
