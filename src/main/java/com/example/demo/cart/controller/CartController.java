package com.example.demo.cart.controller;

import com.example.demo.cart.model.entity.Cart;
import com.example.demo.cart.service.CartService;
import common.util.AppConstants;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Api(value = "cart")
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1/carts", produces = {MediaType.APPLICATION_JSON_VALUE})
public class CartController {
    private final CartService cartService;

    @ApiOperation(value = "create cart", notes = "create cart")
    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> create(@Valid @RequestBody Cart brand){
        return new ResponseEntity<>(cartService.create(brand), HttpStatus.CREATED);
    }

    @ApiOperation(value = "find carts", notes = "find carts")
    @RequestMapping(method = RequestMethod.GET)
    public Page<Cart> findAll(
            @RequestParam(value = "pageNo", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = AppConstants.DEFAULT_SORT_BY, required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = AppConstants.DEFAULT_SORT_DIRECTION, required = false) String sortDir
    ) {
        return cartService.findAll(pageNo, pageSize, sortBy, sortDir);
    }

    @ApiOperation(value = "find cart by id", notes = "find brand by id")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Cart findById(@PathVariable("id") Cart cart) {
        return cart;
    }

    @ApiOperation(value = "delete carts by ids", notes = "delete carts by ids")
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable(name = "id") long id){
        cartService.delete(id);
        return new ResponseEntity<>("Post entity deleted successfully.", HttpStatus.OK);
    }
}
