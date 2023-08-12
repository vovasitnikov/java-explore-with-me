package com.github.explore_with_me.main.comment.model;

import com.github.explore_with_me.main.event.model.Event;
import com.github.explore_with_me.main.user.model.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "comments")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String text;
    @ManyToOne(fetch = FetchType.LAZY,
               cascade = CascadeType.PERSIST)
    @JoinColumn(name = "event_id",
                referencedColumnName = "id",
                nullable = false)
    private Event  event;
    @ManyToOne(fetch = FetchType.LAZY,
               cascade = CascadeType.PERSIST)
    @JoinColumn(name = "author_id",
                referencedColumnName = "id",
                nullable = false)
    private User   author;
    @Column(name = "created")
    private LocalDateTime created;
    @Column(name = "updatedAt")
    private LocalDateTime updated;
}