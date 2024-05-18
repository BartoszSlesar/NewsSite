package com.bard.newssitedata.model;


import com.bard.newssitedata.utils.DateConstraint;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class News {

    private long articleId;

    @JsonIgnore
    private String newsId;
    @NotNull
    @Size(min = 1, max = 255)
    private String source;
    @NotNull
    @Size(min = 1, max = 100)
    private String author;
    @NotNull
    @Size(min = 1, max = 255)
    private String title;
    @NotNull
    @NotBlank
    private String description;
    @Size(max = 255)
    private String url;
    @Size(max = 255)
    private String urlToImage;
    @DateConstraint
    private String publishedAt;
    @NotNull
    private String content;
}
