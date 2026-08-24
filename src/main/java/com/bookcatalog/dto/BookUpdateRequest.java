package com.bookcatalog.dto;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@Setter
@Getter
public class BookUpdateRequest {

    @NotNull(message = "Pages read cannot be null")
    @Min(value = 0,message = "Pages read cannot be negative")
    private Integer pagesRead;

    @NotEmpty(message = "Tags cannot be empty")
    private List<String> tags;
}
