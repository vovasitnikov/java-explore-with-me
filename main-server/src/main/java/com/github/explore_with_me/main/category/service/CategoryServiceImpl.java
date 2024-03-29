package com.github.explore_with_me.main.category.service;

import com.github.explore_with_me.main.category.dto.CategoryOutDto;
import com.github.explore_with_me.main.category.dto.NewCategoryDto;
import com.github.explore_with_me.main.category.mapper.CategoryMapper;
import com.github.explore_with_me.main.category.model.Category;
import com.github.explore_with_me.main.category.repository.CategoryRepository;
import com.github.explore_with_me.main.event.repository.EventRepository;
import com.github.explore_with_me.main.exception.model.ConflictException;
import com.github.explore_with_me.main.exception.model.NotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class CategoryServiceImpl implements CategoryService {
    private final EventRepository eventRepository;
    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    @Transactional
    @Override
    public CategoryOutDto saveCategory(NewCategoryDto newCategoryDto) {
        Category category = categoryMapper.newCategoryDtoToCategory(newCategoryDto);
        categoryRepository.save(category);
        log.info("Категория= " + category + " сохранена");
        return categoryMapper.categoryToCategoryOutDto(category);
    }

    @Override
    public void deleteCategory(Long categoryId) {
        if (!categoryRepository.existsById(categoryId)) {
            throw new NotFoundException("Категория с id= " + categoryId + " не найдена");
        }
        if (!eventRepository.findAllByCategoryId(categoryId).isEmpty()) {
            throw new ConflictException("Категорию удалить нельзя, так как существуют события из этой категории");
        }
        categoryRepository.deleteById(categoryId);
        log.info("Категория с id= " + categoryId + " удалена");
    }

    @Override
    public CategoryOutDto updateCategory(Long categoryId,
                                         NewCategoryDto newCategoryDto) {
        Category oldCategory = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new NotFoundException("Категория с id= " + categoryId + " не найдена"));

        Category updatedCategory = categoryMapper.newCategoryDtoToCategory(newCategoryDto);
        updatedCategory.setId(categoryId);
        categoryRepository.save(updatedCategory);
        log.info("Категория с id= " + categoryId + " изменила название с = " + oldCategory.getName() + " на = "
                + newCategoryDto.getName());
        return categoryMapper.categoryToCategoryOutDto(updatedCategory);
    }

    @Override
    public List<CategoryOutDto> getCategories(int from,
                                              int size) {
        PageRequest pagination = PageRequest.of(from / size,
                size);
        List<Category> paginatedCategories = categoryRepository.findAll(pagination).getContent();
        log.info("Получен список категорий= " + paginatedCategories);
        return categoryMapper.categoriesToCategoriesOutDto(paginatedCategories);
    }

    @Override
    public CategoryOutDto getCategoryById(Long categoryId) {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new NotFoundException("Категория с id= " + categoryId + " не найдена"));
        CategoryOutDto categoryDto = categoryMapper.categoryToCategoryOutDto(category);
        log.info("Получена категория= " + category);
        return categoryDto;
    }
}