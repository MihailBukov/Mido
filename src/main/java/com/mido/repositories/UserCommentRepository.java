package com.mido.repositories;

import com.mido.models.UserComment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserCommentRepository extends JpaRepository<UserComment, Long> {
    List<UserComment> findByWrittenTo_Username(String username);
}
