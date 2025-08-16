package marcelina.example.Digital.Food.Receipt.Organizer.controller;


import marcelina.example.Digital.Food.Receipt.Organizer.model.mapper.dto.ProductDTO;
import marcelina.example.Digital.Food.Receipt.Organizer.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@CrossOrigin(origins = "http://localhost:3000")
public class ProductController {

    @Autowired
    private ProductService productService;


    @GetMapping
    public List<ProductDTO> getAllProducts(){
        return productService.getAllProducts();
    }

}
