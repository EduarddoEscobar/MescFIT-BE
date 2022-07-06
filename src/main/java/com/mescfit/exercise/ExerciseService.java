package com.mescfit.exercise;

import com.mescfit.buckets.BucketName;
import com.mescfit.exceptions.NotFoundException;
import com.mescfit.exercise.category.ExerciseCategoryService;
import com.mescfit.filestore.FileStoreService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@AllArgsConstructor
public class ExerciseService {
    private final ExerciseRepository exerciseRepository;
    private final ExerciseCategoryService exerciseCategoryService;
    private final FileStoreService fileStoreService;

    public Exercise addExercise(Exercise exercise){
        return this.exerciseRepository.save(exercise);
    }

    public Exercise getExerciseById(Long exerciseId) {
        return this.exerciseRepository.findById(exerciseId)
                .orElseThrow(() -> new NotFoundException(String.format("Exercise with id %s does was not found", exerciseId)));
    }

    public List<Exercise> getAllExercises() {
        return exerciseRepository.findAll();
    }

    public Exercise removeExercise(Long exerciseId) {
        Exercise exerciseToDelete =  getExerciseById(exerciseId);
        exerciseRepository.delete(exerciseToDelete);
        return exerciseToDelete;
    }

    public Exercise updateExercise(Exercise exercise, Long exerciseId) {
        Exercise exerciseToUpdate = getExerciseById(exerciseId);
        if(exercise.getExerciseName() != null) exerciseToUpdate.setExerciseName(exercise.getExerciseName());
        if(exercise.getDescription() != null) exerciseToUpdate.setDescription(exercise.getDescription());
        return exerciseRepository.save(exerciseToUpdate);
    }

    public void uploadExerciseThumbnail(Long exerciseId, MultipartFile file) {
        fileStoreService.isFileEmpty(file);
        fileStoreService.isImage(file);
        Exercise exercise = getExerciseById(exerciseId);
        String uploadedFile = fileStoreService.uploadFile(BucketName.EXERCISE_LIBRARY_DEV, exerciseId, file);
        exercise.setExerciseThumbnailLink(uploadedFile);
        exerciseRepository.save(exercise);
    }

    public void uploadExerciseVideo(Long exerciseId, MultipartFile file) {
        fileStoreService.isFileEmpty(file);
        fileStoreService.isVideo(file);
        Exercise exercise = getExerciseById(exerciseId);
        String uploadedFile = fileStoreService.uploadFile(BucketName.EXERCISE_LIBRARY_DEV, exerciseId, file);
        exercise.setExerciseVideoLink(uploadedFile);
        exerciseRepository.save(exercise);
    }

    public byte[] downloadThumbnail(Long exerciseId) {
        Exercise exercise = getExerciseById(exerciseId);
        return fileStoreService.downloadFile(
                BucketName.EXERCISE_LIBRARY_DEV,
                exerciseId,
                exercise.getExerciseThumbnailLink());
    }

    public byte[] downloadVideo(Long exerciseId) {
        Exercise exercise = getExerciseById(exerciseId);
        return fileStoreService.downloadFile(
                BucketName.EXERCISE_LIBRARY_DEV,
                exerciseId,
                exercise.getExerciseVideoLink());
    }

}
