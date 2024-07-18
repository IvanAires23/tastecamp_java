package com.tastecamp.api.models;

import com.tastecamp.api.dtos.RecipeDTO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//anotations
@Data                 //Getters e Setters
@NoArgsConstructor    // Construtor sem argumentos 
@AllArgsConstructor   // Construtor com todos os argumentos
@Entity               // Representa que é uma entidade a ser mapeado no BD
@Table(name = "recipes") // Nome da tabela no banco de dados
public class RecipeModel {

    public RecipeModel(RecipeDTO dto){
        this.title = dto.getTitle();
        this.steps = dto.getSteps();
        this.ingredients = dto.getIngredients();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String ingredients;

    @Column(nullable = false)
    private String steps;
}
