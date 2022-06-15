package com.mescfit.exercise.category;

import com.mescfit.category.Category;
import com.mescfit.exercise.Exercise;
import lombok.*;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class ExerciseCategoryKey implements Serializable {
    @ManyToOne(cascade = CascadeType.REMOVE, optional = false)
    @JoinColumn(name = "exercise_id", nullable = false)
    private Exercise exercise;

    @ManyToOne(cascade = CascadeType.REMOVE, optional = false)
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

}
