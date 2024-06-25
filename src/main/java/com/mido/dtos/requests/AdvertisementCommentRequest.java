package com.mido.dtos.requests;

import com.mido.models.Advertisement;
import com.mido.models.User;
import jakarta.persistence.*;

public record AdvertisementCommentRequest(
        Long advertisementId,
        String comment,
        String writtenBy
) {
}
