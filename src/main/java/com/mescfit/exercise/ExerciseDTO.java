package com.mescfit.exercise;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExerciseDTO {

    private Long id;
    private String exerciseName;
    private String description;

    private List<String> categories;

    public ExerciseDTO(Long id, String exerciseName, String description) {
        this.id = id;
        this.exerciseName = exerciseName;
        this.description = description;
    }
}
