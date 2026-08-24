package com.bookcatalog.exception;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@NoArgsConstructor
@ResponseStatus(code = HttpStatus.NOT_FOUND)
@Getter
public class BookNotFoundException extends RuntimeException{
    private String message;

    public BookNotFoundException(String message) {
        super(message);
        this.message=message;
    }
}

