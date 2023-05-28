package com.mescfit.trackedExercise;

import com.mescfit.appuser.AppUser;
import com.mescfit.exercise.Exercise;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Entity
@AllArgsConstructor
@RequiredArgsConstructor
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

    @Column(name = "tracked_date")
    private LocalDate trackedDate = LocalDate.now();
}
