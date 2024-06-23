package com.mido.services;

import com.mido.dtos.UserCommentDto;
import com.mido.dtos.requests.UserCommentRequest;
import com.mido.mappers.UserCommentMapper;
import com.mido.models.UserComment;
import com.mido.repositories.UserCommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserCommentService {
    private final UserCommentRepository userCommentRepository;

    private final UserCommentMapper userCommentMapper;

    private final UserService userService;

    public void addComment(UserCommentRequest req) {
        UserComment newComment = setCommentFromRequest(req);
        userCommentRepository.saveAndFlush(newComment);
    }

    public UserCommentDto editComment(Long id, UserCommentRequest req) {
        UserComment comment = setCommentFromRequest(req);
        Optional<UserComment> optionalUserComment = userCommentRepository.findById(id);
        UserComment editedComment = getEditedComment(optionalUserComment, comment);
        userCommentRepository.saveAndFlush(editedComment);
        return userCommentMapper.commentToCommentDto(editedComment);
    }

    private UserComment getEditedComment(Optional<UserComment> optionalUserComment, UserComment comment) {
        if(optionalUserComment.isPresent()) {
            UserComment editedComment = new UserComment();

            if(comment.getComment() != null) {
                editedComment.setComment(comment.getComment());
            }

            if(comment.getWrittenBy() != null) {
                editedComment.setWrittenBy(comment.getWrittenBy());
            }

            if(comment.getWrittenTo() != null) {
                editedComment.setWrittenTo(comment.getWrittenTo());
            }

            return editedComment;
        } else {
            throw new IllegalArgumentException("Such comment doesn't exist");
        }
    }

    private UserComment setCommentFromRequest(UserCommentRequest req) {
        UserComment newComment = new UserComment();

        if(req.comment() == null) {
            throw new IllegalArgumentException("Comment is empty");
        }

        newComment.setComment(req.comment());

        if(req.writtenBy() == null || req.writtenTo() == null) {
            throw new IllegalArgumentException("Username is empty");
        }

        if(userService.loadUserByUsername(req.writtenBy()) == null ||
                userService.loadUserByUsername(req.writtenTo()) == null) {
            throw new IllegalArgumentException("There is not such user");
        }

        newComment.setWrittenBy(userService.loadUserByUsername(req.writtenBy()));
        newComment.setWrittenTo(userService.loadUserByUsername(req.writtenTo()));

        return newComment;
    }

    public UserCommentDto getComment(Long id) {
        return userCommentMapper.commentToCommentDto(userCommentRepository.findById(id).orElse(null));
    }

    public void deleteComment(Long id) {
        userCommentRepository.deleteById(id);
    }
}
