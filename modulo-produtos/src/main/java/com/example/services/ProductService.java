package com.example.services;

import com.example.dtos.ProductRecordDto;
import com.example.dtos.ProductResponseDto;
import com.example.exception.ResourceNotFoundException;
import com.example.models.ProductModel;
import com.example.repositories.ProductRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;

    public ProductModel save (ProductRecordDto productRecordDto) {
        var productModel = new ProductModel();
        BeanUtils.copyProperties(productRecordDto, productModel);

        return productRepository.save(productModel);
    }

    public List<ProductResponseDto> findAll() {

        List<ProductModel> products = productRepository.findAll();
        return products.stream()
                .map(product -> new ProductResponseDto(product.getIdProduct(),
                        product.getName(),
                        product.getPrice(),
                        product.getCategory(),
                        product.getCreatedAt(),
                        product.getUpdatedAt()))
                .toList();
    }

    public Object findOne(UUID id) {
        ProductModel product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Produto com ID " + id + " não encontrado"));

        return new ProductResponseDto(product.getIdProduct(),
                product.getName(),
                product.getPrice(),
                product.getCategory(),
                product.getCreatedAt(),
                product.getUpdatedAt());
    }

    public Object updateById(UUID id, ProductRecordDto productRecordDto) {
        ProductModel product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Produto com ID " + id + " não encontrado"));

        BeanUtils.copyProperties(productRecordDto, product);

        productRepository.save(product);

        return new ProductResponseDto(product.getIdProduct(),
                product.getName(),
                product.getPrice(),
                product.getCategory(),
                product.getCreatedAt(),
                product.getUpdatedAt());
    }

    public void deleteById(UUID id) {
        ProductModel product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Produto com ID " + id + " não encontrado"));

        productRepository.delete(product);

    }
}
