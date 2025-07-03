package com.example.dashboard.controllers;

import com.example.dtos.ProductRecordDto;
import com.example.dtos.ProductResponseDto;
import com.example.models.ProductModel;
import com.example.services.ProductService;
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
import java.util.UUID;

@RestController
@RequestMapping("/api/products")
@Tag(name = "Produto", description = "API para gerenciamento de produtos")
public class ProductController {

    @Autowired
    ProductService productService;

    @PostMapping
    @Operation(summary = "Salvar novo produto", description = "Adiciona um novo produto no banco de dados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Retorna os detalhes do produto criado"),
            @ApiResponse(responseCode = "400", description = "Corpo da requisição incompleto")
    })
    public ResponseEntity<ProductModel> saveProduct(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "Corpo da requisição com os dados do novo produto",
            required = true,
            content = @Content(schema = @Schema(implementation = ProductRecordDto.class))
    ) @RequestBody @Valid ProductRecordDto productRecordDto) {

        return ResponseEntity.status(HttpStatus.CREATED).body(productService.save(productRecordDto));

    }

    @GetMapping
    @Operation(summary = "Listar todos os produtos", description = "Retorna uma lista com todos os produtos e seus detalhes")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Retorna a lista de produtos")
    })
    public ResponseEntity<List<ProductResponseDto>> getAllProducts() {

        return ResponseEntity.status(HttpStatus.OK).body(productService.findAll());
    }

    @GetMapping("/{id}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Retorna os detalhes do produto"),
            @ApiResponse(responseCode = "404", description = "Produto não encontrado")
    })
    @Operation(summary = "Buscar produto por ID", description = "Retorna os detalhes do produto")
    public ResponseEntity<Object> getOneProduct(@PathVariable(value = "id") UUID id) {

        return ResponseEntity.status(HttpStatus.OK).body(productService.findOne(id));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar produto por ID", description = "Busca o produto pelo Id informado, e atualiza com os dados informados. Retorna os detalhes do produto atualizado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Retorna os detalhes produto atualizado"),
            @ApiResponse(responseCode = "404", description = "Produto não encontrado"),
            @ApiResponse(responseCode = "400", description = "Corpo da requisição incompleto")
    })
    public ResponseEntity<Object> updateProduct(
            @PathVariable(value = "id") UUID id,
                                                @io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "Corpo da requisição com os dados para a atualização do produto",
            required = true,
            content = @Content(schema = @Schema(implementation = ProductRecordDto.class))
    ) @RequestBody @Valid ProductRecordDto productRecordDto) {

        return ResponseEntity.status(HttpStatus.OK).body(productService.updateById(id, productRecordDto));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deletar produto por ID", description = "Busca o produto pelo Id informado, e remove do banco de dados. Retorna os detalhes do produto removido")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Retorna os detalhes do produto deletado"),
            @ApiResponse(responseCode = "404", description = "Produto não encontrado")
    })
    public ResponseEntity<Object> updateProduct(@PathVariable(value = "id") UUID id) {

        productService.deleteById(id);

        return ResponseEntity.status(HttpStatus.OK).body("Product deleted successfully!");
    }

}

