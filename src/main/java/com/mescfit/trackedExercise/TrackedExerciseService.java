package com.mescfit.trackedExercise;

import com.mescfit.exceptions.NotFoundException;
import com.mescfit.exerciseSet.ExerciseSet;
import com.mescfit.exerciseSet.ExerciseSetRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class TrackedExerciseService {
    private final TrackedExerciseRepository trackedExerciseRepository;
    private final ExerciseSetRepository exerciseSetRepository;

    public List<TrackedExercise> getAllTrackedExercises() {
        return trackedExerciseRepository.findAll();
    }

    public TrackedExercise getTrackedExerciseById(Long trackedExerciseId) {
        return trackedExerciseRepository.findById(trackedExerciseId)
                .orElseThrow(() -> new NotFoundException("TrackedExercise not found with id: " + trackedExerciseId));
    }

    public List<ExerciseSet> getAllExerciseSetsForTrackedExercise(Long trackedExerciseId) {
        return exerciseSetRepository.findAllByTrackedExerciseId(trackedExerciseId);
    }

    public Long createTrackedExercise(TrackedExercise trackedExercise) {
        return trackedExerciseRepository.save(trackedExercise).getTrackedExerciseId();
    }

    public TrackedExercise deleteTrackedExercise(Long trackedExerciseId, Long userId) {
        TrackedExercise trackedExercise = trackedExerciseRepository.findById(trackedExerciseId)
                .orElseThrow(() -> new NotFoundException("TrackedExercise not found with id: " + trackedExerciseId));
        if (!Objects.equals(trackedExercise.getAppUser().getUserId(), userId)) {
            throw new IllegalStateException("You are not allowed to delete tracked exercises that are not yours");
        }
        trackedExerciseRepository.delete(trackedExercise);
        return trackedExercise;
    }
}
