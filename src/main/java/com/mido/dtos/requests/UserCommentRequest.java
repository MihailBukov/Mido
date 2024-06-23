package com.mido.dtos.requests;

public record UserCommentRequest(
        String writtenTo,
        String comment,
        String writtenBy
) {
}
