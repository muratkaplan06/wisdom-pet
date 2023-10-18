package com.muratkaplan.wisdompet.services;

import com.muratkaplan.wisdompet.data.entities.ProductEntity;
import com.muratkaplan.wisdompet.data.repositories.ProductRepository;
import com.muratkaplan.wisdompet.web.errors.NotFoundException;
import com.muratkaplan.wisdompet.web.models.Product;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getAllProducts(){
        Iterable<ProductEntity> entities= productRepository.findAll();
        List<Product> products = new ArrayList<>();
        for(ProductEntity entity: entities){
            products.add(translateDbToWeb(entity));
        }
        return products;
    }
    public Product getProductById(Long id){
        ProductEntity entity = productRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Product not found"));
        return translateDbToWeb(entity);
    }
    public Product createOrUpdateProduct(Product product){
       ProductEntity entity = translateWebToDb(product);
       ProductEntity savedEntity = productRepository.save(entity);
       return translateDbToWeb(savedEntity);
    }
    public void deleteProductById(Long id){
        this.productRepository.deleteById(id);
    }

    private ProductEntity translateWebToDb(Product product) {
        ProductEntity entity = new ProductEntity();
        entity.setId(product.getId());
        entity.setName(product.getName());
        entity.setPrice(product.getPrice());
        entity.setVendorId(product.getVendorId());
        return entity;
    }
    private Product translateDbToWeb(ProductEntity entity) {
        return new Product(
                entity.getId(),
                entity.getName(),
                entity.getPrice(),
                entity.getVendorId()
        );
    }
}
