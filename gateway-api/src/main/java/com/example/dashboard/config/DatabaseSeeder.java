package com.example.dashboard.config;

import com.example.dtos.CategoryRecordDto;
import com.example.services.CategoryService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DatabaseSeeder {

    @Bean
    CommandLineRunner initDatabase(CategoryService categoryService) {
        return args -> {
            // Verifica se já existem categorias cadastradas
            if (categoryService.findAll().isEmpty()) {
                categoryService.save(new CategoryRecordDto("Electronics"));
                categoryService.save(new CategoryRecordDto("Clothing"));
                categoryService.save(new CategoryRecordDto("Books"));
                categoryService.save(new CategoryRecordDto("Home & Garden"));
                categoryService.save(new CategoryRecordDto("Sports"));
                categoryService.save(new CategoryRecordDto("Toys"));
                categoryService.save(new CategoryRecordDto("Automotive"));
                categoryService.save(new CategoryRecordDto("Health & Beauty"));
                System.out.println("Categorias criadas com sucesso.");
            } else {
                System.out.println("Categorias já existem. Seed ignorado.");
            }
        };
    }
}
