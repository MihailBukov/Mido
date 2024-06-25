package com.mido.services;

import com.mido.dtos.AdvertisementCommentDto;
import com.mido.dtos.requests.AdvertisementCommentRequest;
import com.mido.mappers.AdvertisementCommentMapper;
import com.mido.models.AdvertisementComment;
import com.mido.repositories.AdvertisementCommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AdvertisementCommentService {

    private final AdvertisementCommentRepository adRepo;

    private final AdvertisementCommentMapper adMapper;

    private final UserService userService;

    private final AdvertisementService adService;

    public void addComment(AdvertisementCommentRequest req) {
        AdvertisementComment newComment = setCommentFromRequest(req);
        adRepo.saveAndFlush(newComment);
    }

    public AdvertisementCommentDto editComment(Long id, AdvertisementCommentRequest req) {
        Optional<AdvertisementComment> optionalAdvertisementComment = adRepo.findById(id);
        AdvertisementComment editedComment = getEditedComment(optionalAdvertisementComment, req);
        adRepo.saveAndFlush(editedComment);
        return adMapper.adCommentToDto(editedComment);
    }

    private AdvertisementComment getEditedComment(Optional<AdvertisementComment> optionalAdvertisementComment, AdvertisementCommentRequest req) {
        if(optionalAdvertisementComment.isPresent()) {
            AdvertisementComment editedComment = optionalAdvertisementComment.get();

            if(req.comment() != null) {
                editedComment.setComment(req.comment());
            }

            if(req.writtenBy() != null) {
                editedComment.setWrittenBy(req.writtenBy());
            }

            if(req.advertisementId() != null) {
                editedComment.setAdvertisementId(req.advertisementId());
            }

            return editedComment;
        } else {
            throw new IllegalArgumentException("Such comment doesn't exist");
        }
    }

    private AdvertisementComment setCommentFromRequest(AdvertisementCommentRequest req) {
        AdvertisementComment newComment = new AdvertisementComment();

        if(req.comment() == null) {
            throw new IllegalArgumentException("Comment is empty");
        }

        newComment.setComment(req.comment());

        if(req.advertisementId() == null || req.writtenBy() == null) {
            throw new IllegalArgumentException("Usernames are empty");
        }

        if(userService.loadUserByUsername(req.writtenBy()) == null ||
                adService.findById(req.advertisementId()) == null) {
            throw new IllegalArgumentException("There is not such user");
        }

        newComment.setWrittenBy(req.writtenBy());
        newComment.setAdvertisementId(req.advertisementId());

        return newComment;
    }

    public AdvertisementCommentDto getComment(Long id) {
        return adMapper.adCommentToDto(adRepo.findById(id).orElse(null));
    }

    public void deleteComment(Long id) {
        adRepo.deleteById(id);
    }
}
