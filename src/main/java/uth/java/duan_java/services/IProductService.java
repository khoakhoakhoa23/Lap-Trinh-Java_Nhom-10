package uth.java.duan_java.services;

import uth.java.duan_java.models.dtos.ProductDTO;

import java.util.List;

public interface IProductService {
    List<ProductDTO> getAllProducts();

    ProductDTO getProductById(long id);

    ProductDTO createProduct(ProductDTO product);

    ProductDTO updateProduct(ProductDTO product, long id);

    boolean deleteProduct(long id);
}
