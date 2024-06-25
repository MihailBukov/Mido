package com.mido.repositories;

import com.mido.dtos.AdvertisementCommentDto;
import com.mido.models.AdvertisementComment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AdvertisementCommentRepository extends JpaRepository<AdvertisementComment, Long> {

    Optional<List<AdvertisementComment>> findAllByAdvertisementId(Long advertisementId);

}
