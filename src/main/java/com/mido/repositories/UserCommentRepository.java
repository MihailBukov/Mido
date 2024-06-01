package com.mido.repositories;

import com.mido.models.UserComment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserCommentRepository extends JpaRepository<UserComment, Long> {

}
