package marcelina.example.Digital.Food.Receipt.Organizer.service;

import marcelina.example.Digital.Food.Receipt.Organizer.enumerationClasses.ItemCategory;
import marcelina.example.Digital.Food.Receipt.Organizer.model.Product;
import marcelina.example.Digital.Food.Receipt.Organizer.model.mapper.ProductMapper;
import marcelina.example.Digital.Food.Receipt.Organizer.model.mapper.dto.ProductDTO;
import marcelina.example.Digital.Food.Receipt.Organizer.repository.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ProductService {

    @Autowired
    private ProductRepo productRepo;


    @Autowired
    private ProductMapper productMapper;

    public List<ProductDTO> getAllProducts(){
        List<Product> productList = productRepo.findAll();
        return productList.stream()
                .map(p -> productMapper.maptoDto(p))
                .toList();
    }

    public List<ProductDTO> getProductsXCategory(ItemCategory itemCategory){
        List<Product>  productList = productRepo.findByCategory(itemCategory);
        return productList.stream()
                .map(p -> productMapper.maptoDto(p))
                .toList();
    }
}
