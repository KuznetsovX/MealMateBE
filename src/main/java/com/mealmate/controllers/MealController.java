package com.mealmate.controllers;

import com.mealmate.models.Meal;
import com.mealmate.repositories.MealRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/recipes")
public class MealController {

    private final MealRepository mealRepository;

    public MealController(MealRepository mealRepository) {
        this.mealRepository = mealRepository;
    }

    // Get All Meals
    @GetMapping
    public List<Meal> getAllMeals() {
        return mealRepository.findAll();
    }

    // Add a New Meal
    @PostMapping
    public Meal addMeal(@RequestBody Meal meal) {
        return mealRepository.save(meal);
    }

    // Edit an Existing Meal
    @PutMapping("/{id}")
    public ResponseEntity<Meal> updateMeal(@PathVariable Long id, @RequestBody Meal updatedMeal) {
        return mealRepository.findById(id)
                .map(meal -> {
                    meal.setName(updatedMeal.getName());
                    meal.setDescription(updatedMeal.getDescription());
                    mealRepository.save(meal);
                    return ResponseEntity.ok(meal);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    // Delete a Meal
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMeal(@PathVariable Long id) {
        return mealRepository.findById(id)
                .map(meal -> {
                    mealRepository.delete(meal);
                    return ResponseEntity.ok().<Void>build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}
