package com.github.explore_with_me.main.event.repository;

import com.github.explore_with_me.main.comment.model.Comment;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

    List<Comment> findAllByEventId(Long eventId, Pageable pageRequest);

    List<Comment> findAllByEventId(Long eventId);
}