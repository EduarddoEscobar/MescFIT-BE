package com.mescfit.trackedExercise;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TrackedExerciseRepository extends JpaRepository<TrackedExercise, Long> {
    @Query("SELECT t from TrackedExercise t " +
            "WHERE t.appUser.userId = ?1")
    List<TrackedExercise> findTrackedExerciseByUserId(Long userId);
}
