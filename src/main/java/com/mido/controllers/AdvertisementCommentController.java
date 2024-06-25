package com.mido.controllers;

import com.mido.dtos.AdvertisementCommentDto;
import com.mido.dtos.UserCommentDto;
import com.mido.dtos.requests.AdvertisementCommentRequest;
import com.mido.dtos.requests.UserCommentRequest;
import com.mido.models.AdvertisementComment;
import com.mido.services.AdvertisementCommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/advertisementComment")
public class AdvertisementCommentController {

    private final AdvertisementCommentService adCommentService;

    @PostMapping
    public ResponseEntity<Void> createComment(@RequestBody AdvertisementCommentRequest commentReq) {
        adCommentService.addComment(commentReq);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<AdvertisementCommentDto> editComment(@PathVariable Long id, @RequestBody AdvertisementCommentRequest commentReq) {
        AdvertisementCommentDto comment = adCommentService.editComment(id, commentReq);
        return new ResponseEntity<>(comment, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AdvertisementCommentDto> getComment(@PathVariable Long id) {
        AdvertisementCommentDto comment = adCommentService.getComment(id);
        return new ResponseEntity<>(comment, HttpStatus.OK);
    }

    @GetMapping("/comments/{adId}")
    public ResponseEntity<List<AdvertisementCommentDto>> getAllComments(@PathVariable Long adID) {
        List<AdvertisementCommentDto> comments = adCommentService.getAllComments(adID);
        return new ResponseEntity<>(comments, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteComment(@PathVariable Long id) {
        adCommentService.deleteComment(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
