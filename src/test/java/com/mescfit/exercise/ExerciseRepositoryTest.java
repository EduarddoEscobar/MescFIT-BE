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
    void findAllByCategoryNameOnlyFindsEachExerciseOnce() {
        // given
        Exercise exercise = new Exercise(
                "Bench",
                "Chest exercise",
                null,
                null
        );
        Category category = new Category("Chest");
        Category category2 = new Category("Upper Chest");
        ExerciseCategory exerciseCategory = new ExerciseCategory(new ExerciseCategoryKey(
                exercise,
                category));
        ExerciseCategory exerciseCategory2 = new ExerciseCategory(new ExerciseCategoryKey(
                exercise,
                category2));
        underTest.save(exercise);
        categoryRepository.save(category);
        categoryRepository.save(category2);
        exerciseCategoryRepository.save(exerciseCategory);
        exerciseCategoryRepository.save(exerciseCategory2);
        // when
        List<Exercise> result = underTest.findAllByCategoryName("Chest");

        // then
        assertThat(result).isEqualTo(List.of(exercise));
    }

    @Test
    void findAllByCategoryNameAndLikeName() {
        // given
        Exercise exercise = new Exercise(
                "Bench",
                "Chest exercise",
                null,
                null
        );
        Exercise exercise2 = new Exercise(
                "Incline Bench",
                "Shoulder exercise",
                null,
                null
        );

        underTest.save(exercise);
        underTest.save(exercise2);

        Category category = new Category("Chest");
        Category category2 = new Category("Upper Chest");

        categoryRepository.save(category);
        categoryRepository.save(category2);

        ExerciseCategory exerciseCategory = new ExerciseCategory(new ExerciseCategoryKey(
                exercise,
                category));
        ExerciseCategory exerciseCategory2 = new ExerciseCategory(new ExerciseCategoryKey(
                exercise2,
                category2));

        exerciseCategoryRepository.save(exerciseCategory);
        exerciseCategoryRepository.save(exerciseCategory2);
        // when
        List<Exercise> result = underTest.findAllByCategoryName("Chest");

        // then
        assertThat(result).isEqualTo(List.of(exercise, exercise2));
    }
}