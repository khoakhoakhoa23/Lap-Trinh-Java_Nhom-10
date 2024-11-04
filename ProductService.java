package uth.java.duan_java.services;


import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uth.java.duan_java.models.dtos.CartItemDTO;
import uth.java.duan_java.models.dtos.ProductDTO;
import uth.java.duan_java.models.entities.Product;
import uth.java.duan_java.repositories.ProductRepo;

import java.util.List;

@Service
public class ProductService implements IProductService {

    @Autowired
    private ProductRepo productRepo;
    @Autowired
    private ModelMapper modelMapper;


    @Override
    public List<ProductDTO> getAllProducts(){
        List<Product> products = productRepo.findAll();
        if (products.isEmpty()) {
            throw new RuntimeException("No products found");
        }
        return modelMapper.map(products, new TypeToken<List<CartItemDTO>>(){}.getType());
    }
    @Override
    public ProductDTO getProductById(long id){
        Product product = productRepo.findById((long) id)
                .orElseThrow(() -> new RuntimeException("Product not found"));
        return modelMapper.map(product, ProductDTO.class);
    }
    @Override
    public ProductDTO createProduct(ProductDTO product){
        Product newProduct = modelMapper.map(product, Product.class);
        newProduct = productRepo.save(newProduct);
        return modelMapper.map(newProduct, ProductDTO.class);
    }
    @Override
    public ProductDTO updateProduct(ProductDTO product, long id){
        Product existingProduct = productRepo.findById((long) id)
                .orElseThrow(() -> new RuntimeException("Product not found"));
        existingProduct.setProductId(product.getProductId());
        existingProduct.setName(product.getName());
        existingProduct.setDescription(product.getDescription());
        existingProduct.setPrice(product.getPrice());
        productRepo.save(existingProduct);
        return modelMapper.map(existingProduct, ProductDTO.class);

    }

    public boolean deleteProduct(long id){
        Product existingProduct = productRepo.findById((long) id)
                .orElseThrow(() -> new RuntimeException("Product not found"));
        productRepo.delete(existingProduct);
        return true;
    }
}
