package com.example.services;

import com.example.dtos.CategoryRecordDto;
import com.example.dtos.CategoryResponseDto;
import com.example.models.CategoryModel;
import com.example.repositories.CategoryRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    CategoryRepository categoryRepository;

    public CategoryModel save (CategoryRecordDto productRecordDto) {
        var categoryModel = new CategoryModel();
        BeanUtils.copyProperties(productRecordDto, categoryModel);

        return categoryRepository.save(categoryModel);
    }

    public List<CategoryResponseDto> findAll() {

        List<CategoryModel> products = categoryRepository.findAll();
        return products.stream()
                .map(product -> new CategoryResponseDto(product.getIdCategory(),
                        product.getName()))
                .toList();
    }
}
