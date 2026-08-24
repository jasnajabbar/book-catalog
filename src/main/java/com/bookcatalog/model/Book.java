package com.bookcatalog.model;

import com.bookcatalog.dto.BookResponse;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class Book {
    private Integer bookId;
    private String bookName;
    private String authorName;
    private List<String> tags;
    private Integer totalPages;
    private Integer pagesRead;
    private LocalDate createdDate;
    private LocalDateTime lastModified;


    public Book(Integer bookId, String bookName, String authorName, List<String> tags, Integer totalPages, Integer pagesRead, Integer pagesRead1, Double progressPercentage, String readingStatus, Boolean read) {
    }
}


