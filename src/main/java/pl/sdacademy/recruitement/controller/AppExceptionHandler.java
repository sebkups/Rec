package pl.sdacademy.recruitement.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import pl.sdacademy.recruitement.dto.ErrorResponse;
import pl.sdacademy.recruitement.exception.LocationNotFoundException;

@ControllerAdvice
public class AppExceptionHandler {

    @ExceptionHandler({LocationNotFoundException.class})
    public ResponseEntity<ErrorResponse> handleException(LocationNotFoundException e) {
        ErrorResponse dto = new ErrorResponse();
        dto.setLocationId(e.getLocationId());
        dto.setCause(e.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .header("Exception-Class", e.getClass().getCanonicalName())
                .body(dto);
    }

    @ExceptionHandler({Exception.class})
    public ResponseEntity<ErrorResponse> handleRuntimeException(Exception e) {
        ErrorResponse dto = new ErrorResponse();
        dto.setCause(e.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .header("Exception-Class", e.getClass().getCanonicalName())
                .body(dto);
    }
}
