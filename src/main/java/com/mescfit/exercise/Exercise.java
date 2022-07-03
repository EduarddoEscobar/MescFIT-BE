package com.mescfit.exercise;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "exercises")
public class Exercise {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "exercise_id")
    private Long exerciseId;

    @Column(name = "exercise_name", nullable = false)
    private String exerciseName;
    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "exercise_thumbnail_link", unique = true)
    private String exerciseThumbnailLink;

    @Column(name = "exercise_video_link", unique = true)
    private String exerciseVideoLink;

    public Exercise(String exerciseName, String description, String exerciseVideoLink) {
        this.exerciseName = exerciseName;
        this.description = description;
        this.exerciseVideoLink = exerciseVideoLink;
    }
}
