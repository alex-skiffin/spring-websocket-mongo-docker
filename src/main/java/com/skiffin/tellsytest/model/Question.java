package com.skiffin.tellsytest.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;

public class Question {

    private final UUID id;
    private final String content;
    private Integer likeCount;

    public Question(@JsonProperty("id") UUID id,
                    @JsonProperty("content")String content) {
        this.id = id;
        this.content = content;
        this.likeCount = 0;
    }

    public UUID getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public Integer getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(Integer likeCount) {
        this.likeCount = likeCount;
    }

}
