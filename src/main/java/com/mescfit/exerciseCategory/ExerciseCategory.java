package com.mescfit.exerciseCategory;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Table(name = "exercise_categories")
public class ExerciseCategory {
    @EmbeddedId
    private ExerciseCategoryKey id;
}
