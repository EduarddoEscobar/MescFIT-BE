package com.mescfit.exercise;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExerciseDTO {
    private String exerciseName;
    private String description;
    private String exerciseThumbnailLink;
    private String exerciseVideoLink;
    private List<String> categories;
}
