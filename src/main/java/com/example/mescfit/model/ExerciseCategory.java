package com.example.mescfit.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Getter
@Setter
@ToString
@Entity
@Table(name = "exercise_categories")
public class ExerciseCategory {
    @EmbeddedId
    private ExerciseCategoryKey id;
}
