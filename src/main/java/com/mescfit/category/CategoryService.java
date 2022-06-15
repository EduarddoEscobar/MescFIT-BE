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

    public Category addCategory(Category category) {
        if(categoryRepository.categoryWithNameExists(category.getCategoryName())){
            throw new IllegalStateException("Category with the name " + category.getCategoryName() + " already exists");
        }
        return categoryRepository.save(category);
    }

    public Category removeCategory(String name) {
        Category categoryToDelete = categoryRepository.findByCategoryName(name)
                .orElseThrow(() -> new NotFoundException(String.format("Category with name %s was not found", name)));
        categoryRepository.delete(categoryToDelete);
        return categoryToDelete;
    }
}
