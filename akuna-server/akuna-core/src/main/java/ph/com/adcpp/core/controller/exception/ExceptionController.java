package ph.com.adcpp.core.controller.exception;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ph.com.adcpp.commons.exception.RegCodeNotFoundException;
import ph.com.adcpp.commons.exception.RegCodeUsedException;
import ph.com.adcpp.utils.ResponseUtil;

/**
 * @author raymond.galima
 * @date 12/7/2019.
 */
@ControllerAdvice
public class ExceptionController {

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity handleDataIntegrityViolation(ConstraintViolationException ex) {
        return ResponseEntity.ok().body(ResponseUtil.error(ex));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        return ResponseEntity.ok().body(ResponseUtil.error(ex));
    }

    @ExceptionHandler(RegCodeNotFoundException.class)
    public ResponseEntity handleRegCodeNotFoundException(RegCodeNotFoundException ex) {
        return ResponseEntity.ok().body(ResponseUtil.error(ex));
    }

    @ExceptionHandler(RegCodeUsedException.class)
    public ResponseEntity handleRegCodeUsedException(RegCodeUsedException ex) {
        return ResponseEntity.ok().body(ResponseUtil.error(ex));
    }
}
