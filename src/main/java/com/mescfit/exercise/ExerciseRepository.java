package com.mescfit.exercise;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExerciseRepository extends JpaRepository<Exercise, Long> {
    @Query(value = "" +
            "SELECT e.* FROM exercises AS e " +
            "LEFT JOIN exercise_categories AS ec ON e.exercise_id = ec.exercise_id " +
            "LEFT JOIN categories AS c ON ec.category_id = c.category_id " +
            "WHERE c.category_name = ?1",
            nativeQuery = true)
    List<Exercise> findAllByCategoryName(String name);
}
