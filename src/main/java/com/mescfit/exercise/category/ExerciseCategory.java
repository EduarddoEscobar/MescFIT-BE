package com.mescfit.exercise.category;

import javax.persistence.*;

@Entity
@Table(name = "exercise_categories")
public class ExerciseCategory {
    @EmbeddedId
    private ExerciseCategoryKey id;
}
