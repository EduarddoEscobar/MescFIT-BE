package com.mescfit.exerciseSet;

import com.mescfit.trackedExercise.TrackedExercise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExerciseSetRepository extends JpaRepository<ExerciseSet, Long> {
    @Query("SELECT es FROM ExerciseSet es " +
            "LEFT JOIN TrackedExercise te ON es.trackedExercise.trackedExerciseId = te.trackedExerciseId " +
            "WHERE te.trackedExerciseId = ?1")
    List<ExerciseSet> findExerciseSetByTrackedExerciseId(Long trackedExerciseId);

    @Query("SELECT es FROM ExerciseSet es " +
            "LEFT JOIN TrackedExercise te ON es.trackedExercise.trackedExerciseId = te.trackedExerciseId " +
            "LEFT JOIN AppUser u ON te.appUser.userId = u.userId " +
            "WHERE u.userId = ?1")
    List<ExerciseSet> findExerciseSetByUserID(Long userId);

    @Query("SELECT es FROM ExerciseSet es " +
            "LEFT JOIN TrackedExercise te ON es.trackedExercise.trackedExerciseId = te.trackedExerciseId " +
            "LEFT JOIN Exercise e ON te.exercise.exerciseId = e.exerciseId " +
            "WHERE e.exerciseId = ?1")
    List<ExerciseSet> findExerciseSetByExerciseId(Long exerciseId);

    @Query("SELECT es FROM ExerciseSet es " +
            "LEFT JOIN TrackedExercise te ON es.trackedExercise.trackedExerciseId = te.trackedExerciseId " +
            "LEFT JOIN Exercise e ON te.exercise.exerciseId = e.exerciseId " +
            "LEFT JOIN AppUser u ON te.appUser.userId = u.userId " +
            "WHERE u.userId = ?1 AND e.exerciseId = ?2")
    List<ExerciseSet> findExerciseSetByExerciseAndUserId(Long userId, Long exerciseId);

    List<ExerciseSet> findAllByTrackedExercise(TrackedExercise trackedExercise);
}

