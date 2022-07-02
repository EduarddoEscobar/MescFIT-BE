package com.mescfit.exercise;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExerciseConverter {

    public ExerciseDTO entityToDTO(Exercise exercise) {
        return new ExerciseDTO(
            exercise.getExerciseId(),
            exercise.getExerciseName(),
            exercise.getDescription()
        );
    }

    public ExerciseDTO entityToDTO(Exercise exercise, List<String> categories) {
         return new ExerciseDTO(
             exercise.getExerciseId(),
             exercise.getExerciseName(),
             exercise.getDescription(),
             categories
         );
    }

    public Exercise DTOToExercise(ExerciseDTO exerciseDTO) {
        return new Exercise(
                exerciseDTO.getId(),
                exerciseDTO.getExerciseName(),
                exerciseDTO.getDescription()
        );
    }
}
