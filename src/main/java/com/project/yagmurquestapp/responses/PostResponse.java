package com.project.yagmurquestapp.responses;

import com.project.yagmurquestapp.entities.Post;
import lombok.Data;

import java.util.List;

@Data
public class PostResponse {

    Long id;
    Long userId;
    String userName;
    String title;
    String text;
    List<LikeResponse> postLikes;

    public PostResponse(Post entity, List<LikeResponse> likes) { // mapper işlemi yapmış oluyoruz.post entity sini postresponse a dönüştürür
        this.id = entity.getId();
        this.userId = entity.getKisi().getId();
        this.userName = entity.getKisi().getUserName();
        this.title = entity.getTitle();
        this.text = entity.getText();
        this.postLikes = likes;
    }
}

