package com.campuslands.grocery_management.controllers;

import com.campuslands.grocery_management.dtos.ProductDto;
import com.campuslands.grocery_management.services.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService){
        this.productService =productService;
    }


    @PostMapping
    public ResponseEntity<ProductDto> saveProduct(@RequestBody ProductDto productDto){
        return new ResponseEntity<>(productService.saveProduct(productDto), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<ProductDto>> getProducts(){
        return new ResponseEntity<>(productService.getProducts(), HttpStatus.OK);
    }

    @GetMapping("/{productId}")
    public ResponseEntity<ProductDto> findByProductId(@PathVariable Long productId){
        return new ResponseEntity<>(productService.findByProductId(productId), HttpStatus.OK);
    }

    @PutMapping("/{productId}")
    public ResponseEntity<ProductDto> updateProduct(@PathVariable Long productId, @RequestBody ProductDto productDto){
        return new ResponseEntity<>(productService.updateProduct(productId, productDto), HttpStatus.OK);
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<?> deleteProduct(@PathVariable Long productId){
        productService.deleteProduct(productId);
        return new ResponseEntity<>("Product delete success!", HttpStatus.OK);
    }

    @GetMapping("filter")
    public ResponseEntity<List<ProductDto>> findByCategory(@RequestParam String category){
        return new ResponseEntity<>(productService.findByCategory(category), HttpStatus.OK);
    }
}
