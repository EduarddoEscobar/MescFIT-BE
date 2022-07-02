package com.mescfit.exercise;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "exercise_library")
public class Exercise {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "exercise_id")
    private Long exerciseId;

    @Column(name = "exercise_name", nullable = false)
    private String exerciseName;
    @Column(name = "description", nullable = false)
    private String description;
    @Column(name = "exercise_video_link", unique = true)
    private String exerciseVideoLink;

    public Exercise(Long exerciseId, String exerciseName, String description) {
        this.exerciseId = exerciseId;
        this.exerciseName = exerciseName;
        this.description = description;
    }

    public Exercise(String exerciseName, String description, String exerciseVideoLink) {
        this.exerciseName = exerciseName;
        this.description = description;
        this.exerciseVideoLink = exerciseVideoLink;
    }
}
