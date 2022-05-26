package com.project.yagmurquestapp.requests;

import lombok.Data;

//DTO
@Data
public class PostCreateRequest {
    Long id;
    String text;
    String title;
    Long userId;
}
