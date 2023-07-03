package com.bezkoder.springjwt.security.services;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bezkoder.springjwt.models.Comment;
import com.bezkoder.springjwt.repository.CommentRepository;

@Service
public class CommentService {
    private final CommentRepository commentRepository;

    @Autowired
    public CommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }
    
    public List<Comment> getComments() {
        return commentRepository.findAll();
    }
    
    public Comment createComment(Comment comment) {
        // 댓글 생성 로직 구현
        Comment createdComment = commentRepository.save(comment);
        return createdComment;
    }

    public Comment updateComment(Long commentId, Comment updatedComment) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new RuntimeException("Comment not found with id: " + commentId));

        // 수정할 내용 업데이트
        comment.setContent(updatedComment.getContent());

        // 수정된 댓글의 updated_at 필드 업데이트
        comment.setUpdatedAt(LocalDateTime.now()); // 추가

        // 수정된 댓글 저장
        Comment savedComment = commentRepository.save(comment);
        return savedComment;
    }

    public void deleteComment(Long commentId) {
        // 댓글 삭제 로직 구현
        commentRepository.deleteById(commentId);
    }
    
    public List<Comment> getCommentsByBoardId(Long boardId) {
        return commentRepository.findByBoardId(boardId);
    }

    // ...
}

