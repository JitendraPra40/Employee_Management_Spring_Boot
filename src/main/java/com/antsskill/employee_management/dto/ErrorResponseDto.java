package com.antsskill.employee_management.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ErrorResponseDto {

        private LocalDateTime timestamp;
        private int status;
        private String error;
        private String message;
        private List<FieldErrorResponseDto> fieldErrors;
        private String path;

        public ErrorResponseDto(LocalDateTime timestamp, int status, String error,
                                String message, String path) {
            this.timestamp = timestamp;
            this.status = status;
            this.error = error;
            this.message = message;
            this.path = path;
        }
        public ErrorResponseDto(LocalDateTime timestamp, int status, String error,
                             List<FieldErrorResponseDto> fieldErrors, String path) {
            this.timestamp = timestamp;
            this.status = status;
            this.error = error;
            this.fieldErrors = fieldErrors;
            this.path = path;
        }
}

