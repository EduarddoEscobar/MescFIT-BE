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
    private Long id;

    @Column(name = "exercise_name", nullable = false)
    private String exerciseName;
    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "video_type")
    private String videoType;

    @Lob
    @Column(name = "video", nullable = false)
    private byte[] video;

    public Exercise(String exerciseName, String description, String videoType, byte[] video) {
        this.exerciseName = exerciseName;
        this.description = description;
        this.videoType = videoType;
        this.video = video;
    }
}
