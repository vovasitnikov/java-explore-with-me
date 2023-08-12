package com.github.explore_with_me.main.comment.repository;

import com.github.explore_with_me.main.comment.model.Comment;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

    List<Comment> findAllByEventId(Long eventId, Pageable pageRequest);

    List<Comment> findAllByEventId(Long eventId);

    @Query("select c from Comment as c " +
            "join fetch c.event as e " +
            "join fetch c.author as a " +
            "where e.id = :eventId and a.id = :userId")
    List<Comment> findAllByEventIdAndUserId(@Param("userId") Long userId, @Param("eventId") Long eventId);
}