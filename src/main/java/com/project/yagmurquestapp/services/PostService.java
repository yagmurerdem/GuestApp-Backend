package com.project.yagmurquestapp.services;

import com.project.yagmurquestapp.entities.Kisi;
import com.project.yagmurquestapp.entities.Post;
import com.project.yagmurquestapp.repos.PostRepository;
import com.project.yagmurquestapp.requests.PostCreateRequest;
import com.project.yagmurquestapp.requests.PostUpdateRequest;
import com.project.yagmurquestapp.responses.LikeResponse;
import com.project.yagmurquestapp.responses.PostResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PostService {

    private PostRepository postRepository;
    private LikeService likeService;
    private KisiService kisiService;

    public PostService(PostRepository postRepository,
                       KisiService kisiService) {
        this.postRepository = postRepository;
        this.kisiService = kisiService;
    }

    @Autowired
    public void setLikeService(LikeService likeService) {
        this.likeService = likeService;
    }


    public List<PostResponse> getAllPosts(Optional<Long> userId) {
        List<Post> list;
        if(userId.isPresent()) {
            list = postRepository.findByUserId(userId.get()); //eğer user varsa o userın postlarının listesini atıyor list içine
        }else
            list = postRepository.findAll();
        return list.stream().map(p -> { //her bir postun likeını getircez
            List<LikeResponse> likes = likeService.getAllLikesWithParam(Optional.ofNullable(null), Optional.of(p.getId()));  //ilgili posta ait tüm likeları alıcaz
            return new PostResponse(p, likes);}).collect(Collectors.toList());
    }

    public Post getOnePostById(Long postId) {
        return postRepository.findById(postId).orElse(null);
    }

    public Post createOnePost(PostCreateRequest newPostRequest) {
        Kisi kisi = kisiService.getOneUserById(newPostRequest.getUserId());
        if(kisi == null)
            return null;
        Post toSave = new Post();
        toSave.setId(newPostRequest.getId());
        toSave.setText(newPostRequest.getText());
        toSave.setTitle(newPostRequest.getTitle());
        toSave.setKisi(kisi);
        return postRepository.save(toSave);
    }

    public Post updateOnePostById(Long postId, PostUpdateRequest updatePost) {
        Optional<Post> post = postRepository.findById(postId);
        if(post.isPresent()) {
            Post toUpdate = post.get();
            toUpdate.setText(updatePost.getText());
            toUpdate.setTitle(updatePost.getTitle());
            postRepository.save(toUpdate);
            return toUpdate;
        }
        return null;
    }

    public void deleteOnePostById(Long postId) {
        postRepository.deleteById(postId);
    }

}