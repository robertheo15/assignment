package com.example.demo.cart.service.impl;

import com.example.demo.authentication.exception.ResourceNotFoundException;
import com.example.demo.cart.model.entity.CartDetail;
import com.example.demo.cart.repository.CartDetailRepository;
import com.example.demo.cart.service.CartDetailService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class CartDetailServiceImpl implements CartDetailService {

    private final CartDetailRepository repository;

    @Override
    public CartDetail create(CartDetail entity) {
        return repository.save(entity);
    }

    @Override
    public CartDetail update(CartDetail oldEntity, CartDetail newEntity) {
//        return repository.update(oldEntity, newEntity);
        return null;
    }

    @Override
    public void delete(Long id) {
        CartDetail cartDetail = repository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("CartDetail", "id", id));
        repository.delete(cartDetail);
    }

    @Override
    public Page<CartDetail> findAll(int pageNo, int pageSize, String sortBy, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();
        // create Pageable instance
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);

        return repository.findAll(pageable);
    }

    @Override
    public Optional<CartDetail> findById(Long id) {
        return repository.findById(id);
    }
}
