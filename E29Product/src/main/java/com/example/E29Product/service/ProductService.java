package com.example.E29Product.service;

import com.example.E29Product.dto.ProductDto;
import com.example.E29Product.dto.ResponseDto;
import com.example.E29Product.dto.UserDto;
import com.example.E29Product.entity.Product;
import com.example.E29Product.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private RestTemplate restTemplate;

    public ResponseEntity<Product> addProduct(Long userId, Product product) {
        if (getUserRole(Math.toIntExact(userId)) == null) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        if (getUserRole(Math.toIntExact(userId)).equals("seller")) {
            //Set the value of parameter is equals to the value in the body
            product.setUserId(userId);
            productRepository.save(product);
            return new ResponseEntity(HttpStatus.OK);
        }
        return new ResponseEntity(HttpStatus.UNAUTHORIZED);

    }

    public String getUserRole(int userId) {
        try {
            UserDto user = restTemplate.getForObject("http://localhost:8080/api/users/" + userId, UserDto.class);
            return String.valueOf(user.getRole());
        } catch (HttpClientErrorException e) {
            return null;
        }
    }

public ResponseEntity<Boolean> verifyIdRole(int userId){
        try {
            UserDto user = restTemplate.getForObject("http://localhost:8080/api/users/" + userId, UserDto.class);
            if(user.getRole().equals("seller") && user.getId()== userId){
                return ResponseEntity.ok(true);
            }
            else{
                return new ResponseEntity<>(false,HttpStatus.UNAUTHORIZED);
            }
        } catch (HttpClientErrorException e) {
            return new ResponseEntity<>(false, HttpStatus.NOT_FOUND);
        }
}

    public ResponseEntity<List<Product>> getAllProducts(Long userId) {

        ResponseEntity<Boolean> checkIdRole = verifyIdRole(Math.toIntExact(userId));
        if ( checkIdRole.getBody()==true) {
            return new ResponseEntity<>(productRepository.findByUserId(userId),HttpStatus.OK);
            //return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(checkIdRole.getStatusCode());
    }

    public ResponseEntity<ResponseDto> getProduct(Long productId) {

        ResponseDto responseDto = new ResponseDto();
        Optional<Product> product = productRepository.findById(productId);

        if (product.isPresent()) {
            ProductDto productDto = mapToProduct(product.get());

            ResponseEntity<UserDto> responseEntity = restTemplate.
                    getForEntity("http://localhost:8080/api/users/" + product.get().getUserId(), UserDto.class);

            UserDto userDto = responseEntity.getBody();
            System.out.println(responseEntity.getStatusCode());
            responseDto.setProductDto(productDto);
            responseDto.setUserDto(userDto);
            return ResponseEntity.ok(responseDto);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    private ProductDto mapToProduct(Product product) {
        ProductDto productDto = new ProductDto();
        productDto.setId(product.getId());
        productDto.setProductName(product.getProductName());
        productDto.setDescription(product.getDescription());
        return productDto;
    }

    public ResponseEntity<Product> deleteProductById(Long id) {
        Optional<Product> product = productRepository.findById(id);
        if (product.isPresent()) {
            productRepository.deleteById(id);
            return ResponseEntity.ok(product.get());
        }
        return new ResponseEntity(HttpStatus.NOT_FOUND);

    }

}
