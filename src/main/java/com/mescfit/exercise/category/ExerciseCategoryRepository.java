package com.mescfit.exercise.category;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExerciseCategoryRepository extends JpaRepository<ExerciseCategory, ExerciseCategoryKey> {

}
