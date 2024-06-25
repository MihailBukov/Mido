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
        Optional<UserComment> optionalUserComment = userCommentRepository.findById(id);
        UserComment editedComment = getEditedComment(optionalUserComment, req);
        userCommentRepository.saveAndFlush(editedComment);
        return userCommentMapper.commentToCommentDto(editedComment);
    }

    private UserComment getEditedComment(Optional<UserComment> optionalUserComment, UserCommentRequest req) {
        if(optionalUserComment.isPresent()) {
            UserComment editedComment = optionalUserComment.get();

            if(req.comment() != null) {
                editedComment.setComment(req.comment());
            }

            if(req.writtenBy() != null) {
                editedComment.setWrittenBy(userService.loadUserByUsername(req.writtenBy()));
            }

            if(req.writtenTo() != null) {
                editedComment.setWrittenTo(userService.loadUserByUsername(req.writtenTo()));
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

        if(req.writtenTo() == null || req.writtenBy() == null) {
            throw new IllegalArgumentException("Usernames are empty");
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
