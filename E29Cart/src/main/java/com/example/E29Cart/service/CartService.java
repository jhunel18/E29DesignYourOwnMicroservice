package com.example.E29Cart.service;

import com.example.E29Cart.dto.ProductDto;
import com.example.E29Cart.dto.UserDto;
import com.example.E29Cart.entity.Cart;
import com.example.E29Cart.repository.CartRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@AllArgsConstructor
@Service
public class CartService {

    private CartRepository cartRepository;

    private RestTemplate restTemplate;
    public ResponseEntity addOrder(int userId, int productId, int productQty) {

    if(getUserRole(userId) == null  || getProductId(productId)==null){
        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }
    if(getUserRole(userId).equals("buyer")){
        //Set the value of parameter is equals to the value in the body
        Cart cart = new Cart();
        cart.setUserId((long) userId);
        cart.setProductId((long) productId);
        cart.setProductQty(productQty);
        cartRepository.save(cart);
        return new ResponseEntity(HttpStatus.OK);
        //return HttpStatus.OK.toString();
    }
    return new ResponseEntity(HttpStatus.UNAUTHORIZED);
    }

    //Check the user role

    public String getUserRole(int userId){
        try{
            UserDto user = restTemplate.getForObject("http://localhost:8080/api/users/"+userId, UserDto.class);
            return String.valueOf(user.getRole());
        }
        catch (HttpClientErrorException e){
            return null;
        }
    }

    //Get the product info added by the User with the Role Buyer
    public String getProductId(int productId){
        try{
        ProductDto product = restTemplate.getForObject("http://localhost:8081/api/products/"+productId, ProductDto.class);
        return String.valueOf(product.getId());
        }
        catch (HttpClientErrorException e){
            return null;
        }

    }

    public List<Cart> getAllProducts() {
        return cartRepository.findAll();
    }
}
