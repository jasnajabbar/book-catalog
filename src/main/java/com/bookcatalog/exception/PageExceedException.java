package com.bookcatalog.exception;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@NoArgsConstructor
@ResponseStatus(code = HttpStatus.BAD_REQUEST)
@Getter
public class PageExceedException extends RuntimeException {
    String message;

    public PageExceedException(String message) {
        this.message = message;
    }
}
