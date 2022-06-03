package com.example.mescfit.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Data
@Table(name = "exercise_categories")
public class ExerciseCategory {
    @EmbeddedId
    private ExerciseCategoryKey id;
}
