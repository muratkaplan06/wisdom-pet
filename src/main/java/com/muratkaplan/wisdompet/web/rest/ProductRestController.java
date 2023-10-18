package com.muratkaplan.wisdompet.web.rest;

import com.muratkaplan.wisdompet.services.ProductService;
import com.muratkaplan.wisdompet.web.errors.BadRequestException;
import com.muratkaplan.wisdompet.web.models.Product;
import jakarta.websocket.server.PathParam;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductRestController {
    private final ProductService productService;

    @GetMapping()
    public List<Product> getAll() {
        return productService.getAllProducts();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Product create(@RequestBody Product product) {
        return productService.createOrUpdateProduct(product);
    }

    @GetMapping("/{id}")
    public Product getProduct(@PathVariable("id") Long id) {
        return productService.getProductById(id);
    }

    @PutMapping("/{id}")
    public Product updateProduct(@PathVariable("id") Long id, @RequestBody Product product) {
        if (!id.equals(product.getId())) {
            throw new BadRequestException("Product id does not match");
        }
        product.setId(id);
        return productService.createOrUpdateProduct(product);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteProduct(@PathVariable("id") Long id) {
        productService.deleteProductById(id);
    }
}
