package com.example.dashboard.controllers;


import com.example.dtos.CategoryRecordDto;
import com.example.dtos.CategoryResponseDto;
import com.example.models.CategoryModel;
import com.example.services.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
@Tag(name = "Categoria", description = "API para gerenciamento das categorias")
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    @PostMapping
    @Operation(summary = "Salvar nova categoria", description = "Adiciona uma nova categoria no banco de dados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Retorna os detalhes da categoria criada"),
            @ApiResponse(responseCode = "400", description = "Corpo da requisição incompleto")
    })
    public ResponseEntity<CategoryModel> saveProduct(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Corpo da requisição com os dados da nova categoria",
                    required = true,
                    content = @Content(schema = @Schema(implementation = CategoryRecordDto.class))
            ) @RequestBody @Valid CategoryRecordDto categoryRecordDto) {

        return ResponseEntity.status(HttpStatus.CREATED).body(categoryService.save(categoryRecordDto));

    }

    @GetMapping
    @Operation(summary = "Listar todos as categorias", description = "Retorna uma lista com todos as categorias e seus detalhes")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Retorna a lista de categorias")
    })
    public ResponseEntity<List<CategoryResponseDto>> getAllProducts() {

        return ResponseEntity.status(HttpStatus.OK).body(categoryService.findAll());
    }
}
