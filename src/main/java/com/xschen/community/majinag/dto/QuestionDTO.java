package com.xschen.community.majinag.dto;

import com.xschen.community.majinag.model.User;
import lombok.Data;

@Data
public class QuestionDTO {

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

    private User user;
}
