package com.github.explore_with_me.main.requests.model;

import com.github.explore_with_me.main.event.model.Event;
import com.github.explore_with_me.main.requests.status.Status;
import com.github.explore_with_me.main.user.model.User;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "requests")
@Data
public class Request {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "event_id",
                referencedColumnName = "id")
    private Event event;
    @ManyToOne
    @JoinColumn(name = "requester_id",
                referencedColumnName = "id")
    private User requester;
    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private Status status;
    @Column(name = "created")
    private LocalDateTime created;
}
