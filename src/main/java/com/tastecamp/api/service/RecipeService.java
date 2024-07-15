package com.tastecamp.api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.tastecamp.api.dtos.RecipeDTO;
import com.tastecamp.api.models.RecipeModel;
import com.tastecamp.api.repositories.RecipeRepository;

@Service
public class RecipeService {
     final RecipeRepository recipeRepository;

    RecipeService(RecipeRepository recipeRepository){
        this.recipeRepository = recipeRepository;   
    }

    public List<RecipeModel> findAll(){
        return recipeRepository.findAll();
    }

    public Optional<RecipeModel> findById(Long id){
        return recipeRepository.findById(id);
    }

    public Optional<RecipeModel> save(RecipeDTO dto){
        if(recipeRepository.exexistsByTitle(dto.getTitle())){
            return Optional.empty();
        }
        RecipeModel recipe = new RecipeModel(dto);
        return Optional.of(recipeRepository.save(recipe));
    }

    public RecipeModel update(RecipeDTO dto, Long id){
        RecipeModel newRecepi = new RecipeModel(dto);  
        newRecepi.setId(id);
        return newRecepi;
    }

    public void deleteRecipe(Long id){
        recipeRepository.deleteById(id);
    }
}
