package com.example.demo.product.controller;

import com.example.demo.product.model.entity.Product;
import com.example.demo.product.service.ProductService;
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

@Api(value = "products")
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1/products", produces = {MediaType.APPLICATION_JSON_VALUE})
public class ProductController {

    private final ProductService productService;

    @ApiOperation(value = "create product", notes = "create products")
    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> create(@Valid @RequestBody Product product){
        return new ResponseEntity<>(productService.create(product), HttpStatus.CREATED);
    }

    @ApiOperation(value = "find products", notes = "find products")
    @RequestMapping(method = RequestMethod.GET)
    public Page<Product> findAll(
            @RequestParam(value = "pageNo", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = AppConstants.DEFAULT_SORT_BY, required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = AppConstants.DEFAULT_SORT_DIRECTION, required = false) String sortDir
    ) {
        return productService.findAll(pageNo, pageSize, sortBy, sortDir);
    }

    @ApiOperation(value = "find product by id", notes = "find product by id")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Product findById(@PathVariable("id") Product cart) {
        return cart;
    }

    @ApiOperation(value = "delete product by ids", notes = "delete product by ids")
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable(name = "id") long id){
        productService.delete(id);
        return new ResponseEntity<>("Product deleted successfully.", HttpStatus.OK);
    }
}
