package com.github.explore_with_me.main.category.controller;

import com.github.explore_with_me.main.category.dto.CategoryOutDto;
import com.github.explore_with_me.main.category.dto.NewCategoryDto;
import com.github.explore_with_me.main.category.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/admin/categories")
@RequiredArgsConstructor
public class CategoryAdminController {

    private final CategoryService categoryService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public CategoryOutDto postCategory(@RequestBody @Valid NewCategoryDto newCategoryDto) {
        return categoryService.saveCategory(newCategoryDto);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void deleteCategory(@PathVariable long id) {
        categoryService.deleteCategory(id);
    }

    @ResponseStatus(HttpStatus.OK)
    @PatchMapping("/{id}")
    public CategoryOutDto updateCategory(@RequestBody @Valid NewCategoryDto newCategoryDto,
                                         @PathVariable Long id) {
        return categoryService.updateCategory(id, newCategoryDto);
    }
}
