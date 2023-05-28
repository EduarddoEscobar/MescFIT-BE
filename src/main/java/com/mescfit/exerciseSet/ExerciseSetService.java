package com.mescfit.exerciseSet;

import com.mescfit.exceptions.NotFoundException;
import com.mescfit.trackedExercise.TrackedExercise;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class ExerciseSetService {
    private final ExerciseSetRepository exerciseSetRepository;

    public List<ExerciseSet> getAllExerciseSets() {
        return exerciseSetRepository.findAll();
    }

    public ExerciseSet getExerciseSetById(Long id) {
        return exerciseSetRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Exercise Set not found with id: " + id));
    }

    public List<ExerciseSet> getAllByExerciseId(Long exerciseId) {
        return exerciseSetRepository.findAllByExerciseId(exerciseId);
    }

    public List<ExerciseSet> getAllByUserId(Long userId) {
        return exerciseSetRepository.findAllByUserId(userId);
    }

    public List<ExerciseSet> getAllByUserIdAndExerciseId(Long userId, Long exerciseId) {
        return exerciseSetRepository.findAllByExerciseAndUserId(userId, exerciseId);
    }

    public List<ExerciseSet> getAllByTrackedExerciseId(Long trackedExerciseId) {
        return exerciseSetRepository.findAllByTrackedExerciseId(trackedExerciseId);
    }

    public Long createExerciseSet(ExerciseSet exerciseSet) {
        return exerciseSetRepository.save(exerciseSet).getExerciseSetId();
    }

    public Long updateExerciseSet(Long exerciseSetId, ExerciseSet exerciseSet, Long userId) {
        ExerciseSet exerciseSetToUpdate = getExerciseSetById(exerciseSetId);
        if (exerciseSet.getTrackedExercise() != null) {
            exerciseSetToUpdate.setTrackedExercise(exerciseSet.getTrackedExercise());
        }

        if (exerciseSet.getIsKilos() != null) {
            exerciseSetToUpdate.setIsKilos(exerciseSet.getIsKilos());
        }

        if (exerciseSet.getReps() != null) {
            exerciseSetToUpdate.setReps(exerciseSet.getReps());
        }

        if (exerciseSet.getWeight() != null) {
            exerciseSetToUpdate.setWeight(exerciseSet.getWeight());
        }
        TrackedExercise trackedExercise = exerciseSetToUpdate.getTrackedExercise();
        if (trackedExercise != null) {
            if (!Objects.equals(trackedExercise.getAppUser().getUserId(), userId)) {
                throw new IllegalStateException("You are not allowed to delete tracked exercises that are not yours");
            }
        }

        exerciseSetRepository.save(exerciseSetToUpdate);
        return exerciseSetToUpdate.getExerciseSetId();
    }

    public ExerciseSet deleteExerciseSet(Long exerciseSetId, Long userId) {
        ExerciseSet exerciseSetToDelete = getExerciseSetById(exerciseSetId);
        TrackedExercise trackedExercise = exerciseSetToDelete.getTrackedExercise();
        if (trackedExercise != null) {
            if (!Objects.equals(trackedExercise.getAppUser().getUserId(), userId)) {
                throw new IllegalStateException("You are not allowed to delete tracked exercises that are not yours");
            }
        }
        exerciseSetRepository.delete(exerciseSetToDelete);
        return exerciseSetToDelete;
    }
}
