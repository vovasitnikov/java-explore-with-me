package com.github.explore_with_me.main.compilation.model;

import com.github.explore_with_me.main.event.model.Event;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "compilations")
@Data
public class Compilation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinTable(
            name = "compilation_event",
            joinColumns = @JoinColumn(name = "compilation_id"),
            inverseJoinColumns = @JoinColumn(name = "event_id"))
    private List<Event> event;

    @Column(name = "pinned")
    private boolean pinned;

    @Column(name = "title")
    private String title;
}