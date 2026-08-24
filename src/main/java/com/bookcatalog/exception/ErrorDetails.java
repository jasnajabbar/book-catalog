package com.bookcatalog.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
@Setter
public class ErrorDetails {
    public LocalDateTime timeStamp;
    public String message;
    public String path;
}
