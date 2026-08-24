package com.bookcatalog.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@Getter
@Setter
public class BookRequest {

    @NotBlank(message = "Book name cannot be empty")
    private String bookName;

    @NotBlank(message = "Author name cannot be empty")
    private String authorName;

    @NotEmpty(message = "Tags cannot be empty")
    private List<String> tags;

    @NotNull(message = "Total pages cannot be null")
    @Min(value = 1,message = "Total pages must be greater to 0")
    private Integer totalPages;

//    @NotNull(message = "Total pages cannot be null")
//    @Min(value = 0,message = "Total pages cannot be negative")
//    private Integer pagesRead;
}
