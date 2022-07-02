package com.mescfit.exercise.category;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExerciseCategoryRepository extends JpaRepository<ExerciseCategory, ExerciseCategoryKey> {
    @Query("SELECT c.id.category.categoryName FROM ExerciseCategory c " +
            "WHERE c.id.exercise.exerciseId = ?1")
    List<String> findAllFromExercise(Long exercise_id);
}
