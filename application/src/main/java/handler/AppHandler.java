package handler;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class AppHandler {

    private static final Logger log = LogManager.getLogger(AppHandler.class);

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler
    public ErrorResponse handleGlobalExceptions(Exception e, WebRequest request) {
        log.error("Internal server error");
        return new ErrorResponse(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                new Date(),
                e.getMessage(),
                request.getDescription(false));
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ValidationViolationResponse handleValidationViolation(MethodArgumentNotValidException e) {
        final List<ValidationViolationResponse.Violation> violations = e.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(error -> new ValidationViolationResponse.Violation(error.getField(), error.getDefaultMessage()))
                .collect(Collectors.toList());
        log.warn("Invalid request parameters");
        return new ValidationViolationResponse(violations);
    }
}
