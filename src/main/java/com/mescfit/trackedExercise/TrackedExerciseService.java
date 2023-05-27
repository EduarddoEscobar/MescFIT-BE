package com.mescfit.trackedExercise;

import com.mescfit.exerciseSet.ExerciseSet;
import com.mescfit.exerciseSet.ExerciseSetRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TrackedExerciseService {
    private final TrackedExerciseRepository trackedExerciseRepository;
    private final ExerciseSetRepository exerciseSetRepository;

    public List<TrackedExercise> getAllTrackedExercises() {
        return trackedExerciseRepository.findAll();
    }

    public List<ExerciseSet> getAllExerciseSetsForTrackedExercise(TrackedExercise trackedExercise) {
        return exerciseSetRepository.findAllByTrackedExercise(trackedExercise);
    }
}
