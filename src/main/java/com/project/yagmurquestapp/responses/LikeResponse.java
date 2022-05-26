package com.project.yagmurquestapp.responses;

import com.project.yagmurquestapp.entities.Like;
import lombok.Data;

@Data
public class LikeResponse {

    Long id;
    Long userId;
    Long postId;

    public LikeResponse(Like entity) {
        this.id = entity.getId();
        this.userId = entity.getKisi().getId();
        this.postId = entity.getPost().getId();
    }}