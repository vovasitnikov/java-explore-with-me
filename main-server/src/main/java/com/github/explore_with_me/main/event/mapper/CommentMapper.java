package com.github.explore_with_me.main.event.mapper;

import com.github.explore_with_me.main.comment.dto.CommentDto;
import com.github.explore_with_me.main.comment.model.Comment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CommentMapper {

    @Mapping(target = "authorName",
             source = "comment.author.name")
    CommentDto commentToCommentDto(Comment comment);

    @Mapping(target = "authorName",
             source = "comment.author.name")
    List<CommentDto> commentToCommentDto(List<Comment> comments);
}