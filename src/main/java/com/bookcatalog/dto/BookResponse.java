package com.bookcatalog.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@Getter
@Setter
public class BookResponse {
    private Integer bookId;
    private String bookName;
    private String authorName;
    private List<String> tags;
    private Integer totalPages;
    private Integer pagesRead;
    private Double progressPercentage;
    private String readingStatus;
    private String Message;
}