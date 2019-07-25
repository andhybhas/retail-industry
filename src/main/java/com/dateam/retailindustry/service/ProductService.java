package com.dateam.retailindustry.service;

import com.dateam.retailindustry.dto.ProductReqDTO;
import com.dateam.retailindustry.dto.ProductRespDTO;
import com.dateam.retailindustry.entity.Product;
import com.dateam.retailindustry.exception.DataNotFoundException;
import com.dateam.retailindustry.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;

    public List<Product> findAll() {return productRepository.findAll();}

    public Product findById(Long id) {
        try {
            Optional<Product> optionalProduct = productRepository.findById(id);
            return optionalProduct.get();
        }
        catch (Exception e) {
            throw new DataNotFoundException("Product Not Found");
        }
    }

    public Product findByProductName(String productName) {
        try {
            Optional<Product> optionalProduct = productRepository.findByProductName(productName);
            return optionalProduct.get();
        }
        catch (Exception e) {
            throw new DataNotFoundException("Product Gak ADA!");
        }
    }

    public Object create (ProductReqDTO productReqDTO) {
        Product product = new Product();
        product.setProductName(productReqDTO.getProductName());
        product.setProductPrice(productReqDTO.getProductPrice());
        product.setProductStock(productReqDTO.getProductStock());

        productRepository.save(product);

        ProductRespDTO productRespDTO = new ProductRespDTO();
        productRespDTO.setProductId(product.getProductId());
        productRespDTO.setProductName(product.getProductName());
        productRespDTO.setProductPrice(product.getProductPrice());
        productRespDTO.setProductStock(product.getProductStock());

        return productReqDTO;

    }

    public ProductReqDTO update (Long id, ProductReqDTO productReqDTO) {
        Optional<Product> optionalProduct = productRepository.findById(id);
        if (!optionalProduct.isPresent()) throw new DataNotFoundException("Product Not Found");

        Product product = new Product();
        product.setProductId(id);
        product.setProductName(productReqDTO.getProductName());
        product.setProductPrice(productReqDTO.getProductPrice());
        product.setProductStock(productReqDTO.getProductStock());

        ProductRespDTO productRespDTO = new ProductRespDTO();
        productRespDTO.setProductId(id);
        productRespDTO.setProductName(product.getProductName());
        productRespDTO.setProductPrice(product.getProductPrice());
        productRespDTO.setProductStock(product.getProductStock());

        productRepository.save(product);
        return productReqDTO;

    }

    public void delete (Long id) {
        productRepository.deleteById(id);
    }

}
