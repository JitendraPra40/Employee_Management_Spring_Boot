package com.antsskill.employee_management.exception;

import com.antsskill.employee_management.dto.ErrorResponseDto;
import com.antsskill.employee_management.dto.FieldErrorResponseDto;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<?> handleResourceNotFound(ResourceNotFoundException ex, HttpServletRequest request) {
        return buildError(HttpStatus.NOT_FOUND, ex.getMessage(), request.getRequestURI());
    }

    @ExceptionHandler(ResourceAlreadyExistsException.class)
    public ResponseEntity<?> handleResourceAlreadyExists(ResourceAlreadyExistsException ex, HttpServletRequest request){
        return buildError(HttpStatus.CONFLICT, ex.getMessage(), request.getRequestURI());
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<?> handleBadRequest(BadRequestException ex, HttpServletRequest request){
        return buildError(HttpStatus.BAD_REQUEST, ex.getMessage(), request.getRequestURI());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleException(Exception ex, HttpServletRequest request){
        return buildError(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage(), request.getRequestURI());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleValidationErrors(
            MethodArgumentNotValidException ex,
            HttpServletRequest request) {

        List<FieldErrorResponseDto> fieldErrors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(errors -> new FieldErrorResponseDto(
                        errors.getField(),
                        errors.getDefaultMessage()
                ))
                .toList();

        ErrorResponseDto response = new ErrorResponseDto(
                LocalDateTime.now(),
                HttpStatus.BAD_REQUEST.value(),
                HttpStatus.BAD_REQUEST.getReasonPhrase(),
                fieldErrors,
                request.getRequestURI()
        );

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);

    }


                        //     -------------- option: 1 ----------------------
                        //        StringBuilder errors = new StringBuilder();
                        //        ex.getBindingResult().getFieldErrors().forEach(error -> {
                        //            errors.append(error.getField())
                        //                    .append(": ")
                        //                    .append(error.getDefaultMessage())
                        //                    .append("; ");
                        //        });
                        //        return buildError(
                        //                HttpStatus.BAD_REQUEST,
                        //                errors.toString(),
                        //                request.getRequestURI()
                        //        );
                        //    }

                        //    -------------- option: 2 ----------------------
                        //         List<String> errors = ex.getBindingResult()
                        //                .getFieldErrors()
                        //                .stream()
                        //                .map(error -> error.getField() + ": " + error.getDefaultMessage())
                        //                .toList();
                        //
                        //        return buildErrorList(
                        //                HttpStatus.BAD_REQUEST,
                        //                errors.toString(),
                        //                request.getRequestURI()
                        //        );
                        //    }




    private ResponseEntity<ErrorResponseDto> buildError(HttpStatus status, String message, String path){
        ErrorResponseDto error = new ErrorResponseDto(
                LocalDateTime.now(),
                status.value(),
                status.getReasonPhrase(),
                message,
                path
        );
        return new ResponseEntity<>(error, status);
    }
}

