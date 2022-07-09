package com.mescfit.exercise;

import com.mescfit.categories.Category;
import com.mescfit.categories.CategoryRepository;
import com.mescfit.exerciseCategory.ExerciseCategory;
import com.mescfit.exerciseCategory.ExerciseCategoryKey;
import com.mescfit.exerciseCategory.ExerciseCategoryRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class ExerciseRepositoryTest {
    @Autowired
    private ExerciseRepository underTest;
    @Autowired
    private ExerciseCategoryRepository exerciseCategoryRepository;
    @Autowired
    private CategoryRepository categoryRepository;

    @AfterEach
    void tearDown() {
        underTest.deleteAll();
        exerciseCategoryRepository.deleteAll();
        categoryRepository.deleteAll();
    }

    @Test
    void findAllByCategoryNameWorks() {
        // given
        Exercise exercise = new Exercise(
                "Bench",
                "Chest exercise",
                null,
                null
        );
        Category category = new Category("Chest");
        ExerciseCategory exerciseCategory = new ExerciseCategory(new ExerciseCategoryKey(
                exercise,
                category));
        underTest.save(exercise);
        categoryRepository.save(category);
        exerciseCategoryRepository.save(exerciseCategory);
        // when
        List<Exercise> result = underTest.findAllByCategoryName("Chest");

        // then
        assertThat(result).isEqualTo(List.of(exercise));
    }
}