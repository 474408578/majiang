package com.xschen.community.majinag.model;

import lombok.Data;

@Data
public class Question {
    private Integer id;

    private String title;

    private String description;

    private Integer creator;

    private Integer commentCount;

    private Integer likeCount;

    private Integer viewCount;

    private String tag;

    private Long gmtCreate;

    private Long gmtModified;
}