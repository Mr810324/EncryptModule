package hk.gov.ehr.sfk.encryption.exection;

/**
 * @ClassName GlobalExceptionHandler
 * @Description TODO
 * @date 0:24
 * @Version 1.0
 */

import hk.gov.ehr.sfk.encryption.entity.resultDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.HandlerMethod;


@ControllerAdvice
@Order(100)
@Slf4j
public class GlobalExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public ResponseEntity methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e, HandlerMethod handlerMethod) {

        BindingResult result = e.getBindingResult();
        HttpStatus status = HttpStatus.resolve(400);
        StringBuilder rs = new StringBuilder();
        if (result.hasErrors()) {
            for (ObjectError p : result.getAllErrors()) {
                FieldError fieldError = (FieldError) p;
                rs.append(fieldError.getDefaultMessage() + ".");
            }
        }

        return new ResponseEntity<>(new resultDTO(400, rs.toString(),new String[]{}), status);
    }

}
