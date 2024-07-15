package com.tastecamp.api.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.tastecamp.api.dtos.RecipeDTO;
import com.tastecamp.api.models.RecipeModel;
import com.tastecamp.api.service.RecipeService;

import jakarta.validation.Valid;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("/recipes")
public class RecipeController {

    final RecipeService recipeService;

    RecipeController(RecipeService recipeService){
        this.recipeService = recipeService;   
    }

    @GetMapping
    public ResponseEntity<List<RecipeModel>> getRecipes(){
        List<RecipeModel> recipes = recipeService.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(recipes);

    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getRecipeById(@PathVariable Long id){
        Optional<RecipeModel> recipe = recipeService.findById(id);

        if(!recipe.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não encontrado");
        }

        return ResponseEntity.status(HttpStatus.OK).body(recipe);
    }

    @PostMapping
    public ResponseEntity<Object> createRecipe(@RequestBody @Valid RecipeDTO body) { 
        Optional<RecipeModel> recipe = recipeService.save(body);
        
        if(!recipe.isPresent()){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Titulo já existe");
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(recipeService.save(body));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateRecipe(@PathVariable Long id, @RequestBody RecipeDTO body){
        Optional<RecipeModel> recipe = recipeService.findById(id);

        if(!recipe.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não encontrado");
        }

        return ResponseEntity.status(HttpStatus.OK).body(recipeService.update(body, id));
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteRecipe(@PathVariable Long id){
        recipeService.deleteRecipe(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
    
}
