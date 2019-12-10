package ph.com.adcpp.commons;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

/**
 * @Author raymond.galima
 * @date 12/3/2019.
 */
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BaseResponse {

    private String timestamp;
    private Integer status;
    private String message;
    private Object payload;

    public BaseResponse() {
        initSuccessValues();
    }

    public BaseResponse(Object payload){
        initSuccessValues();
        this.payload = payload;
    }

    public BaseResponse(String message, Object payload) {
        initSuccessValues();
        this.message = message;
        this.payload = payload;
    }

    public BaseResponse(Integer status, String message, Object payload) {
        this.status = status;
        this.message = message;
        this.payload = payload;
        this.timestamp = LocalDateTime.now().toString();
    }

    private void initSuccessValues() {
        this.status = HttpStatus.OK.value();
        this.message = HttpStatus.OK.getReasonPhrase();
        this.timestamp = LocalDateTime.now().toString();
    }
}
