package com.example.E29Cart.restcontroller;

import com.example.E29Cart.entity.Cart;
import com.example.E29Cart.repository.CartRepository;
import com.example.E29Cart.service.CartService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@AllArgsConstructor
@RestController
@RequestMapping("api/cart")
public class CartController {

    private CartService cartService;
    @PostMapping()
    public ResponseEntity addOrder(@RequestParam int userId, int productId, int productQty)
    {
        return cartService.addOrder(userId,productId, productQty);
    }
    @GetMapping(path = "/viewall")
    public List<Cart> getAllProducts(){
        return cartService.getAllProducts();
    }
}
