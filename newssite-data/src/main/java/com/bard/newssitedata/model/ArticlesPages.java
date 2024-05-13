package com.bard.newssitedata.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonPropertyOrder({
        "totalPages",
        "articles"
})
public class ArticlesPages {

    @JsonProperty("total_pages")
    private int totalPages;

    @JsonProperty("articles")
    private List<Article> results;
}
