package com.mescfit.category;

import com.mescfit.exceptions.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    public boolean categoryWithNameExists(String name) {
        return categoryRepository.findByCategoryName(name).isEmpty();
    }

    public void addCategory(String name) {
        categoryRepository.save(new Category(name));
    }

    public void removeCategory(String name) {
        Category categoryToDelete = categoryRepository.findByCategoryName(name)
                .orElseThrow(() -> new NotFoundException(String.format("Category with name %s was not found", name)));
        categoryRepository.delete(categoryToDelete);
    }
}
