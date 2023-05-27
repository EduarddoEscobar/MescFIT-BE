package com.mescfit.exerciseSet;

import com.mescfit.trackedExercise.TrackedExercise;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "exercise_sets")
public class ExerciseSet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "exercise_set_id")
    private Long exerciseSetId;

    @ManyToOne(cascade = CascadeType.REMOVE, optional = false)
    @JoinColumn(name = "tracked_exercise_id", nullable = false)
    private TrackedExercise trackedExercise;

    @Column(name = "reps")
    private Integer reps;

    @Column(name = "weight")
    private Integer weight;

    @Column(name = "is_kilos")
    private Boolean isKilos = false;
}
