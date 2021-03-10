package applicationa.demo.util;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@ControllerAdvice
@SuppressWarnings("unused")
public class ControllerAdviceCustom extends ResponseEntityExceptionHandler {

    @Autowired
    public ControllerAdviceCustom() {
    }

    @Data
    private static class Response {
        private String error;
        private String exception;
        private String message;
        private String path;
        private Integer status;
        private Timestamp timestamp;
        private List<ObjectError> errors;
    }

    private Response responseFactory(HttpStatus status, Exception e, String message, String path) {
        Response response = new Response();
        response.setError(status.name().toLowerCase());
        response.setException(e.getClass().getName());
        response.setMessage(message);
        response.setPath(path);
        response.setStatus(status.value());
        response.setTimestamp(Timestamp.valueOf(LocalDateTime.now()));
        return response;
    }

    @Data
    private static class ImportResponse {
        private String error;
        private String exception;
        private String message;
        private String path;
        private Integer status;
        private Timestamp timestamp;
        private List<List<ObjectError>> errors;
    }
    
    @ExceptionHandler(value = {ValidationErrorException.class})
    protected ResponseEntity<Object> handleConflict(ValidationErrorException ex, WebRequest r,
                                                    HttpServletRequest request) {
        Response body = responseFactory(HttpStatus.CONFLICT, ex, ValidationErrorException.message,
                request.getRequestURI());
        body.setErrors(ex.getErrors());
        return handleExceptionInternal(ex, body, new HttpHeaders(), HttpStatus.CONFLICT, r);
    }

    @ExceptionHandler(value = {
            DataNotFoundException.class
    })
    protected ResponseEntity<Object> handleCustomException(Exception ex, WebRequest r, HttpServletRequest request) {
        Response body = responseFactory(HttpStatus.BAD_REQUEST, ex, ex.getMessage(), request.getRequestURI());
        return handleExceptionInternal(ex, body, new HttpHeaders(), HttpStatus.BAD_REQUEST, r);
    }

    @Override
    protected ResponseEntity<Object> handleMissingServletRequestParameter(MissingServletRequestParameterException ex,
                                                                          HttpHeaders headers,
                                                                          HttpStatus status,
                                                                          WebRequest request) {
        Response body = responseFactory(HttpStatus.BAD_REQUEST, ex, ex.getMessage(), request.getContextPath());
        return handleExceptionInternal(ex, body, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

}