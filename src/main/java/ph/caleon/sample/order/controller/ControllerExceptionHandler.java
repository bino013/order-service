package ph.caleon.sample.order.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import ph.caleon.sample.order.httpbody.Response;
import ph.caleon.sample.order.service.ServiceException;
import ph.caleon.sample.order.service.ValidationException;

import java.util.ArrayList;
import java.util.List;

/**
 * @author arvin on 2/15/20
 **/
@ControllerAdvice(annotations = {RestController.class})
public class ControllerExceptionHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(ControllerExceptionHandler.class);

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<Response> handleRequestParamError(HttpMessageNotReadableException ex) {
        LOGGER.error("Validation error occurred.", ex);
        return new ResponseEntity<>(new Response(null, ex.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Response> handleRequestParamError(MethodArgumentNotValidException ex) {
        LOGGER.error("Validation error occurred.", ex);
        List<String> errors = new ArrayList<String>();
        for(ObjectError error : ex.getBindingResult().getAllErrors()) {
            errors.add(error.getDefaultMessage());
        }
        return new ResponseEntity<>(new Response(null, errors.toString()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<Response> handleRequestParamError(MethodArgumentTypeMismatchException ex) {
        LOGGER.error("Validation error occurred.", ex);
        return new ResponseEntity<>(new Response(null, ex.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ServiceException.class)
    public ResponseEntity<Response> handleServiceException(Exception ex) {
        LOGGER.error("Service exception error occurred.", ex);
        return new ResponseEntity<>(new Response(null, ex.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Response> handleValidationException(Exception ex) {
        LOGGER.error("Validation exception error occurred.", ex);
        return new ResponseEntity<>(new Response(null, ex.getMessage()), HttpStatus.BAD_REQUEST);
    }

}
