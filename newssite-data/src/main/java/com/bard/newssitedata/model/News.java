package com.bard.newssitedata.model;


import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class News {

    private long articleId;
    private String newsId;
    private String source;
    private String author;
    private String title;
    private String description;
    private String url;
    private String urlToImage;
    private String publishedAt;
    private String content;
}
