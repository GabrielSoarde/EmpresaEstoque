package com.soardev.estoque_soardev.controllers;

import com.soardev.estoque_soardev.entities.LoginRequest;
import com.soardev.estoque_soardev.entities.Product;
import com.soardev.estoque_soardev.entities.User;
import com.soardev.estoque_soardev.services.ProductService;
import com.soardev.estoque_soardev.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private ProductService productService;

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody User user){
        String message = userService.register(user);
        if(message.equals("Email já cadastrado!")){
            return ResponseEntity.badRequest().body(message);
        }
        return ResponseEntity.ok(message);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest){
        if(userService.login(loginRequest.getEmail(), loginRequest.getPassword())){
            return ResponseEntity.ok("Login realizado com sucesso!");
        }
        return ResponseEntity.badRequest().body("Senha ou email inválidos!");
    }

    @PreAuthorize("hasAuthority('USER')")
    @GetMapping("/estoque")
    public ResponseEntity<List<Product>> listProducts() {
        return ResponseEntity.ok(productService.listProduct());
    }

}
