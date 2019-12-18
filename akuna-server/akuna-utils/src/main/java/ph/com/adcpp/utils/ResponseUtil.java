package ph.com.adcpp.utils;

import org.springframework.http.HttpStatus;
import ph.com.adcpp.commons.response.BaseResponse;

/**
 * @author raymond.galima
 * @date 12/7/2019.
 */
public final class ResponseUtil {

    private ResponseUtil(){
        //To hide constructor
    }

    public static BaseResponse success() {
        return new BaseResponse();
    }

    public static BaseResponse success(String message) {
        return new BaseResponse(message);
    }

    public static BaseResponse success(Object payload) {
        return new BaseResponse(payload);
    }

    public static BaseResponse success(String message, Object payload) {
        return new BaseResponse(message, payload);
    }

    public static BaseResponse success(Integer status, String message, Object payload) {
        return new BaseResponse(status, message, payload);
    }

    public static <T extends Exception> BaseResponse error(T ex) {
        BaseResponse response = new BaseResponse();
        response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        response.setMessage(ex.getMessage());
        return response;
    }
}
