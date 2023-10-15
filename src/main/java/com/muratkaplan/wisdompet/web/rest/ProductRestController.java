package com.muratkaplan.wisdompet.web.rest;

import com.muratkaplan.wisdompet.services.ProductService;
import com.muratkaplan.wisdompet.web.models.Product;
import jakarta.websocket.server.PathParam;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductRestController {
    private final ProductService productService;

    @GetMapping
    public List<Product> getAll(){
        return productService.getAllProducts();
    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Product create(@RequestBody Product product){
        return productService.createOrUpdateProduct(product);
    }
    @GetMapping("/{id}")
    public Product getProduct(@PathVariable("id") Long id){
        return productService.getProductById(id);
    }
}
