package com.project.yagmurquestapp.services;

import com.project.yagmurquestapp.entities.Kisi;
import com.project.yagmurquestapp.entities.Like;
import com.project.yagmurquestapp.entities.Post;
import com.project.yagmurquestapp.repos.LikeRepository;
import com.project.yagmurquestapp.requests.LikeCreateRequest;
import com.project.yagmurquestapp.responses.LikeResponse;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class LikeService {

    private LikeRepository likeRepository;
    private KisiService kisiService;
    private PostService postService;

    public LikeService(LikeRepository likeRepository,KisiService kisiService,
                       PostService postService) {
        this.likeRepository = likeRepository;
        this.kisiService = kisiService;
        this.postService = postService;
    }

    public List<LikeResponse> getAllLikesWithParam(Optional<Long> userId, Optional<Long> postId) {
        List<Like> list;
        if(userId.isPresent() && postId.isPresent()) {
            list = likeRepository.findByUserIdAndPostId(userId.get(), postId.get());
        }else if(userId.isPresent()) {
            list = likeRepository.findByUserId(userId.get());
        }else if(postId.isPresent()) {
            list = likeRepository.findByPostId(postId.get());
        }else
            list = likeRepository.findAll();
        return list.stream().map(like -> new LikeResponse(like)).collect(Collectors.toList());
    }

    public Like getOneLikeById(Long LikeId) {
        return likeRepository.findById(LikeId).orElse(null);
    }

    public Like createOneLike(LikeCreateRequest request) {
        Kisi kisi = kisiService.getOneUserById(request.getUserId());
        Post post = postService.getOnePostById(request.getPostId());
        if(kisi != null && post != null) {
            Like likeToSave = new Like();
            likeToSave.setId(request.getId());
            likeToSave.setPost(post);
            likeToSave.setKisi(kisi);
            return likeRepository.save(likeToSave);
        }else
            return null;
    }

    public void deleteOneLikeById(Long likeId) {
        likeRepository.deleteById(likeId);
    }


}