package com.mescfit.category;

import com.mescfit.exceptions.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    public boolean categoryWithNameDoesNotExists(String name) {
        return !categoryRepository.categoryWithNameExists(name);
    }

    public void addCategory(String name) {
        if(categoryRepository.categoryWithNameExists(name)){
            throw new IllegalStateException("Category with the name" + name + " already exists");
        }
        categoryRepository.save(new Category(name));
    }

    public void removeCategory(String name) {
        Category categoryToDelete = categoryRepository.findByCategoryName(name)
                .orElseThrow(() -> new NotFoundException(String.format("Category with name %s was not found", name)));
        categoryRepository.delete(categoryToDelete);
    }
}
