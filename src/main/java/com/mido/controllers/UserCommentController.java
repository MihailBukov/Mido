package com.mido.controllers;

import com.mido.dtos.UserCommentDto;
import com.mido.dtos.requests.UserCommentRequest;
import com.mido.services.UserCommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/comment")
public class UserCommentController {
    private final UserCommentService userCommentService;

    @PostMapping
    public ResponseEntity<Void> createComment(@RequestBody UserCommentRequest commentReq) {
        userCommentService.addComment(commentReq);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<UserCommentDto> editComment(@PathVariable Long id, @RequestBody UserCommentRequest commentReq) {
        UserCommentDto comment = userCommentService.editComment(id, commentReq);
        return new ResponseEntity<>(comment, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserCommentDto> getComment(@PathVariable Long id) {
        UserCommentDto comment = userCommentService.getComment(id);
        return new ResponseEntity<>(comment, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteComment(@PathVariable Long id) {
        userCommentService.deleteComment(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
