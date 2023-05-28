package com.mescfit.exerciseSet;

import com.mescfit.appuser.AppUser;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/api/v1/exercise-sets")
@RequiredArgsConstructor
public class ExerciseSetController {
    private final ExerciseSetService exerciseSetService;

    @GetMapping
    public List<ExerciseSet> getAllExerciseSets(
            @RequestParam(name = "exercise", required = false) Long exerciseId,
            @RequestParam(name = "user", required = false) Long userId
    ) {
        if (exerciseId != null && userId != null) {
            return exerciseSetService.getAllByUserIdAndExerciseId(userId, exerciseId);
        } else if (exerciseId != null) {
            return exerciseSetService.getAllByExerciseId(exerciseId);
        } else if (userId != null) {
            return exerciseSetService.getAllByUserId(userId);
        }
        return exerciseSetService.getAllExerciseSets();
    }

    @GetMapping("/{id}")
    public ExerciseSet getExerciseSetById(@PathVariable Long id) {
        return exerciseSetService.getExerciseSetById(id);
    }

    @PostMapping
    public Long createExerciseSet(@RequestBody ExerciseSet exerciseSet) {
        return exerciseSetService.createExerciseSet(exerciseSet);
    }

    @PutMapping("/{exerciseSetId}")
    private Long deleteTrackedExercise(@PathVariable Long exerciseSetId, @RequestBody ExerciseSet exerciseSet, HttpServletRequest request) {
        AppUser user = (AppUser) request.getAttribute("user");
        return exerciseSetService.updateExerciseSet(exerciseSetId, exerciseSet, user.getUserId());
    }

    @DeleteMapping("/{exerciseSetId}")
    public ExerciseSet deleteExerciseSet(@PathVariable Long exerciseSetId, HttpServletRequest request) {
        AppUser user = (AppUser) request.getAttribute("user");
        return exerciseSetService.deleteExerciseSet(exerciseSetId, user.getUserId());
    }
}
