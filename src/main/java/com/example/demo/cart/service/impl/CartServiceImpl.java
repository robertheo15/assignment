package com.example.demo.cart.service.impl;

import com.example.demo.authentication.exception.ResourceNotFoundException;
import com.example.demo.cart.model.entity.Cart;
import com.example.demo.cart.repository.CartRepository;
import com.example.demo.cart.service.CartService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class CartServiceImpl implements CartService {

    private final CartRepository repository;

    @Override
    public Cart create(Cart entity) {
        return repository.save(entity);
    }

    @Override
    public Cart update(Cart oldEntity, Cart newEntity) {
        return null;
    }

    @Override
    public void delete(Long id) {
        Cart cart = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Cart", "id", id));
        repository.delete(cart);
    }

    @Override
    public Page<Cart> findAll(int pageNo, int pageSize, String sortBy, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();
        // create Pageable instance
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);

        return repository.findAll(pageable);
    }

    @Override
    public Optional<Cart> findById(Long id) {
        return repository.findById(id);
    }
}
