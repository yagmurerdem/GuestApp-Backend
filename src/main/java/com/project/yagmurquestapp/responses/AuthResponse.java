package com.project.yagmurquestapp.responses;

import lombok.Data;

//authenticate olduktan sonra userid kullanmak için
@Data
public class AuthResponse {
    String message;
    Long userId;
}
