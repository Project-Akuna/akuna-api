package ph.com.adcpp.core.controller.exception;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ph.com.adcpp.utils.ResponseUtil;

/**
 * @Author raymond.galima
 * @date 12/7/2019.
 */
@ControllerAdvice
public class ExceptionController {

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity handleDataIntegrityViolation(ConstraintViolationException ex) {
        return ResponseEntity.ok().body(ResponseUtil.error(ex));
    }
}
