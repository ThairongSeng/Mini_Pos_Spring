package co.thairong.mini_pos.exception;

import co.thairong.mini_pos.base.BaseError;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestControllerAdvice
public class ApiException {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public BaseError<?> handleValidationException(MethodArgumentNotValidException e) {

        List<Map<String, String>> errors = new ArrayList<>();

        for (FieldError fieldError : e.getFieldErrors()) {
            Map<String, String> errorDetails = new HashMap<>();
            errorDetails.put("name", fieldError.getField());
            errorDetails.put("message", fieldError.getDefaultMessage());
            errors.add(errorDetails);
        }

        return BaseError.builder()
                .status(false)
                .code(HttpStatus.BAD_REQUEST.value())
                .message("Validation is error, please check detail message.")
                .errors(errors)
                .timestamp(LocalDateTime.now())
                .build();
    }

    @ExceptionHandler(ResponseStatusException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public BaseError<?> handleServiceException(ResponseStatusException e) {

        return BaseError.builder()
                .status(false)
                .code(e.getStatusCode().value())
                .message("Something went wrong..! Please check.")
                .errors(e.getReason())
                .timestamp(LocalDateTime.now())
                .build();
    }
}
