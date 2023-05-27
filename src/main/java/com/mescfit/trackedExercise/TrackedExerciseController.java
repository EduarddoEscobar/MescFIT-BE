package com.mescfit.trackedExercise;

import com.mescfit.appuser.AppUser;
import com.mescfit.exerciseSet.ExerciseSet;
import com.mescfit.exerciseSet.ExerciseSetService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/api/v1/tracked-exercises")
@RequiredArgsConstructor
public class TrackedExerciseController {
    private final TrackedExerciseService trackedExerciseService;
    private final ExerciseSetService exerciseSetService;

    @GetMapping
    private List<TrackedExercise> getAllTrackedExercises() {
        return trackedExerciseService.getAllTrackedExercises();
    }

    @GetMapping("/{trackedExerciseId}")
    private TrackedExercise getTrackedExerciseById(@PathVariable Long trackedExerciseId) {
        return trackedExerciseService.getTrackedExerciseById(trackedExerciseId);
    }

    @GetMapping("/{trackedExerciseId}/sets")
    private List<ExerciseSet> getTrackedExerciseSets(@PathVariable Long trackedExerciseId) {
        return null;
    }

    @PostMapping
    private Long createTrackedExercise(TrackedExercise trackedExercise) {
        return trackedExerciseService.createTrackedExercise(trackedExercise);
    }

    @DeleteMapping("/{trackedExerciseId}")
    private Long deleteTrackedExercise(@PathVariable Long trackedExerciseId, HttpServletRequest request) {
        AppUser user = (AppUser) request.getAttribute("user");
        return trackedExerciseService.deleteTrackedExercise(trackedExerciseId, user.getUserId());
    }
}
