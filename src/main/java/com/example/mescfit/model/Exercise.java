package com.example.mescfit.model;

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
    private Integer exercise_id;

    @Column(name = "exercise_name", nullable = false)
    private String exercise_name;
    @Column(name = "description", nullable = false)
    private String description;
    private String videoType;

    @Lob
    private byte[] video;

    public Exercise(String exercise_name, String description, String videoType, byte[] video) {
        this.exercise_name = exercise_name;
        this.description = description;
        this.videoType = videoType;
        this.video = video;
    }
}
