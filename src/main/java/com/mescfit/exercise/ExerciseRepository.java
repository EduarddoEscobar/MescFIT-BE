package com.mescfit.exercise;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExerciseRepository extends JpaRepository<Exercise, Long> {
    @Query(value = "" +
            "SELECT exercise_id, exercise_name, description, exercise_thumbnail_link, exercise_video_link " +
            "FROM (" +
            "     SELECT e.*, row_number() over(PARTITION BY e.exercise_id ORDER BY e.exercise_id) AS copies " +
            "     FROM exercises e" +
            "     LEFT JOIN exercise_categories ec ON e.exercise_id = ec.exercise_id " +
            "     LEFT JOIN categories c ON ec.category_id = c.category_id " +
            "     WHERE c.category_name LIKE %?1%" +
            " ) res " +
            "WHERE res.copies = 1",
            nativeQuery = true)
    List<Exercise> findAllByCategoryName(String category);
}
