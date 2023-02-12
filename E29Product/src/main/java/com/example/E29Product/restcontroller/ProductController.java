package com.example.E29Product.restcontroller;

import com.example.E29Product.dto.ResponseDto;
import com.example.E29Product.entity.Product;
import com.example.E29Product.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/products")
@AllArgsConstructor
public class ProductController {

    private ProductService productService;

    @PostMapping
    public ResponseEntity<Product> addProduct(@RequestParam Long userId, @RequestBody Product product){
        return productService.addProduct(userId, product);
    }

    @GetMapping(path = "/viewall")
    public ResponseEntity<List<Product>> getAllProducts(@RequestParam Long userId){
        return productService.getAllProducts(userId);
    }

    @GetMapping(path = "{id}")
    public ResponseEntity<ResponseDto> getProduct(@PathVariable("id") Long productId){
        return productService.getProduct(productId);
    }

    @DeleteMapping(path = "/delete/{id}")
    public ResponseEntity<Product> deleteProductById(@PathVariable Long id){
        return productService.deleteProductById(id);
    }


}
