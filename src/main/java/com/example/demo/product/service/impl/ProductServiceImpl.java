package com.example.demo.product.service.impl;

import com.example.demo.authentication.exception.ResourceNotFoundException;
import com.example.demo.product.model.entity.Product;
import com.example.demo.product.repository.ProductRepository;
import com.example.demo.product.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository repository;

    @Override
    public Product create(Product entity) {
        return repository.save(entity);
    }

    @Override
    public Product update(Product oldEntity, Product newEntity) {
//        return repository.update(oldEntity, newEntity);
        return null;
    }

    @Override
    public void delete(Long id) {
        Product product = repository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Product", "id", id));
        repository.delete(product);
    }

    @Override
    public Page<Product> findAll(int pageNo, int pageSize, String sortBy, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();
        // create Pageable instance
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);

        return repository.findAll(pageable);
    }

    @Override
    public Optional<Product> findById(Long id) {
        return repository.findById(id);
    }
}
