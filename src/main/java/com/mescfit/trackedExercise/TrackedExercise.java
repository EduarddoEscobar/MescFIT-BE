package com.mescfit.trackedExercise;

import com.mescfit.appuser.AppUser;
import com.mescfit.exercise.Exercise;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tracked_exercises")
public class TrackedExercise {
    @Id
    @Column(name = "tracked_exercise_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long trackedExerciseId;

    @ManyToOne(cascade = CascadeType.REMOVE, optional = false)
    @JoinColumn(name = "app_user_id", nullable = false)
    private AppUser appUser;

    @ManyToOne(optional = false)
    @JoinColumn(name = "exercise_id", nullable = false)
    private Exercise exercise;


}
