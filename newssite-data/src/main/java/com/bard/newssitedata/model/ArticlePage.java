package com.bard.newssitedata.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.List;


@JsonPropertyOrder({
        "page",
        "nextPage",
        "totalResults",
        "results",
})
public class ArticlePage {


    private int page;
    private int nextPage;

    @JsonProperty("totalResults")
    private int totalResults;

    @JsonProperty("articles")
    private List<Article> results;
}
