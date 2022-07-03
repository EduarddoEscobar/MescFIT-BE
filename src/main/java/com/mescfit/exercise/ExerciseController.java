package com.mescfit.exercise;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Controller
public class ExerciseController {
    private final ExerciseService exerciseService;

    public ExerciseController(ExerciseService exerciseService) {
        this.exerciseService = exerciseService;
    }

    @GetMapping("/")
    public String get(Model model) {
        List<Exercise> exercises = this.exerciseService.getAllExercises();
        model.addAttribute("exercises", exercises);
        return "doc";
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
