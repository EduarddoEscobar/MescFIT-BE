package com.example.mescfit.model;

import com.example.mescfit.exercise.Exercise;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@Getter
@Setter
@Embeddable
public class ExerciseCategoryKey implements Serializable {
    @ManyToOne(cascade = CascadeType.REMOVE, optional = false)
    @JoinColumn(name = "exercise_id", nullable = false)
    private Exercise exercise;

    @ManyToOne(cascade = CascadeType.REMOVE, optional = false)
    @JoinColumn(name = "category_id", nullable = false)
    private Categories categories;

}
