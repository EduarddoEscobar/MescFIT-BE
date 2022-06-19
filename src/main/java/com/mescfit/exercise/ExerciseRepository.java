package com.mescfit.exercise;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExerciseRepository extends JpaRepository<Exercise, Long> {
    @Query("SELECT e.id, e.exerciseName, e.description FROM Exercise e")
    List<ExerciseDTO> findAllWithoutVideo();
}