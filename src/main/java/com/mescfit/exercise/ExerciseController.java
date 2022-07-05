package com.mescfit.exercise;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
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
    }

    @PostMapping(
            path = "/{exerciseId}/video/upload",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public void uploadExerciseVideo(@PathVariable Long exerciseId,
                                    @RequestParam("file")MultipartFile file) {

    }
//    TODO: Fix these upload and download methods so that they use AWS s3
//    @PostMapping("/uploadFiles")
//    public String uploadMultipleFiles(@RequestParam("files") MultipartFile[] files) throws IOException {
//        for(MultipartFile file: files){
//            exerciseService.addExercise("Exercise", "Description", file);
//        }
//        return "redirect:/";
//    }
//
//    @GetMapping("/downloadFile/{id}")
//    public ResponseEntity<ByteArrayResource> downloadFile(@PathVariable Long id) {
//        Exercise exercise = this.exerciseService.getExerciseById(id);
//        return ResponseEntity.ok()
//                .contentType(MediaType.parseMediaType(exercise.getVideoType()))
//                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment:filename=\"" + exercise.getExerciseName() + "\"")
//                .body(new ByteArrayResource(exercise.getVideo()));
//    }

}
