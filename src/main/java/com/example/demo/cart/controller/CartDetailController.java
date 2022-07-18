package com.example.demo.cart.controller;

import com.example.demo.cart.model.entity.CartDetail;
import com.example.demo.cart.service.CartDetailService;
import common.util.AppConstants;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
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

@Api(value = "cartDetail")
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1/cart-details", produces = {MediaType.APPLICATION_JSON_VALUE})
public class CartDetailController {

    private final CartDetailService cartDetailService;

    @ApiOperation(value = "create cart detail", notes = "create cart detail")
    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> create(@Valid @RequestBody CartDetail cartDetail){
        return new ResponseEntity<>(cartDetailService.create(cartDetail), HttpStatus.CREATED);
    }

    @ApiOperation(value = "find carts", notes = "find carts")
    @RequestMapping(method = RequestMethod.GET)
    public Page<CartDetail> findAll(
            @RequestParam(value = "pageNo", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = AppConstants.DEFAULT_SORT_BY, required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = AppConstants.DEFAULT_SORT_DIRECTION, required = false) String sortDir
    ) {
        return cartDetailService.findAll(pageNo, pageSize, sortBy, sortDir);
    }

    @ApiOperation(value = "find cart detail id", notes = "find cart detail by id")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public CartDetail findById(@PathVariable("id") CartDetail cartDetail) {
        return cartDetail;
    }

    @ApiOperation(value = "delete cart detail by ids", notes = "delete cart detail by ids")
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable(name = "id") long id){
        cartDetailService.delete(id);
        return new ResponseEntity<>("cart detail entity deleted successfully.", HttpStatus.OK);
    }
}
