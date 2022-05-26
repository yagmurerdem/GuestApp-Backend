package com.project.yagmurquestapp.services;

import com.project.yagmurquestapp.entities.Comment;
import com.project.yagmurquestapp.entities.Kisi;
import com.project.yagmurquestapp.entities.Post;
import com.project.yagmurquestapp.repos.CommentRepository;
import com.project.yagmurquestapp.requests.CommentCreateRequest;
import com.project.yagmurquestapp.requests.CommentUpdateRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommentService {
    private CommentRepository commentRepository;
    private KisiService kisiService;
    private PostService postService;


    public CommentService(CommentRepository commentRepository, KisiService kisiService, PostService postService) {
        this.commentRepository = commentRepository;
        this.kisiService = kisiService;
        this.postService = postService;
    }

    public List<Comment> getAllCommentWithParam(Optional<Long> userId, Optional<Long> postId) {
        if (userId.isPresent() && postId.isPresent()) {
            return commentRepository.findByUserIdAndPostId(userId.get(), postId.get());
        } else if (userId.isPresent()) {
            return commentRepository.findByUserId(userId.get());
        } else if (postId.isPresent()) {
            return commentRepository.findByPostId(postId.get());
        } else {
            return commentRepository.findAll();
        }

    }

    public Comment getOneCommentById(Long commentId) {
        return commentRepository.findById(commentId).orElse(null);
    }

    public Comment createOneComment(CommentCreateRequest request) {
        Kisi kisi = kisiService.getOneUserById(request.getUserId());
        Post post = postService.getOnePostById(request.getPostId());
        if (kisi != null && post != null) {
            Comment commentToSave = new Comment();
            commentToSave.setId(request.getId());
            commentToSave.setPost(post);
            commentToSave.setKisi(kisi);
            commentToSave.setText(request.getText());
            return commentRepository.save(commentToSave);
        } else
            return null;

    }

    public Comment updateOneCommentById(Long commentId, CommentUpdateRequest request) {
        Optional<Comment> comment = commentRepository.findById(commentId);
        if (comment.isPresent()) {
            Comment commentToUpdate = comment.get();
            commentToUpdate.setText(request.getText());
            return commentRepository.save(commentToUpdate);
            // comment.get().setText(commentUpdateRequest.getText());
        } else
            return null;

    }

    public void deleteOneCommentById(Long commentId) {
        commentRepository.deleteById(commentId);
    }
}