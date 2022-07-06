package com.mescfit.exercise;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/v1/exercises")
@CrossOrigin("*")
public class ExerciseController {
    private final ExerciseService exerciseService;

    public ExerciseController(ExerciseService exerciseService) {
        this.exerciseService = exerciseService;
    }

    @GetMapping
    public List<Exercise> getAllExercises() {
        return exerciseService.getAllExercises();
    }

    @PostMapping
    public Exercise addExercise(@RequestBody Exercise exercise) {
        return exerciseService.addExercise(exercise);
    }

    @PostMapping(
            path = "/{exerciseId}/thumbnail/upload",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public void uploadExerciseThumbnail(@PathVariable Long exerciseId,
                                        @RequestParam("file")MultipartFile file) {
        exerciseService.uploadExerciseThumbnail(exerciseId, file);
    }

    @GetMapping("/{exerciseId}/thumbnail/download")
    public byte[] downloadExerciseThumbnail(@PathVariable Long exerciseId) {
        return exerciseService.downloadThumbnail(exerciseId);
    }

    @PostMapping(
            path = "/{exerciseId}/video/upload",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public void uploadExerciseVideo(@PathVariable Long exerciseId,
                                    @RequestParam("file")MultipartFile file) {
        exerciseService.uploadExerciseVideo(exerciseId, file);
    }

    @GetMapping("/{exerciseId}/video/download")
    public byte[] downloadExerciseVideo(@PathVariable Long exerciseId) {
        return exerciseService.downloadVideo(exerciseId);
    }

}
