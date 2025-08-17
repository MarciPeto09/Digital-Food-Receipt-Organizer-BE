package marcelina.example.Digital.Food.Receipt.Organizer.controller;

import marcelina.example.Digital.Food.Receipt.Organizer.model.mapper.dto.*;
import marcelina.example.Digital.Food.Receipt.Organizer.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/search")
@CrossOrigin(origins = "http://localhost:3000")
public class SearchController {

    @Autowired
    private BasketItemService basketItemService;

    @Autowired
    private ProductService productService;

    @Autowired
    private ReceiptService receiptService;

    @Autowired
    private ReceiptItemsService receiptItemsService;

    @Autowired
    private UserService userService;

    @Autowired
    private VendorService vendorService;

    @GetMapping("/vendors")
    public List<VendorDTO> getAllVendors() {
        return vendorService.getAllVendors();
    }

    @GetMapping("/users")
    public List<UserDTO> getAllUsers(){
        return userService.getAllUsers();
    }

    @GetMapping("/receipt-items")
    public List<ReceiptItemDTO> getAllItems(){
        return receiptItemsService.getAllItems();
    }

    @GetMapping("/receipts")
    public List<ReceiptDTO> getAllReceipt(){
        return receiptService.getAllReceipt();
    }

    @GetMapping("/products")
    public List<ProductDTO> getAllProducts(){
        return productService.getAllProducts();
    }
}

