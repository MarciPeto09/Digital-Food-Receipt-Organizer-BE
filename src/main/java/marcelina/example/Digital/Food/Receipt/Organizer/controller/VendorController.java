package marcelina.example.Digital.Food.Receipt.Organizer.controller;

import marcelina.example.Digital.Food.Receipt.Organizer.model.mapper.dto.ProductDTO;
import marcelina.example.Digital.Food.Receipt.Organizer.model.mapper.dto.VendorDTO;
import marcelina.example.Digital.Food.Receipt.Organizer.service.VendorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/vendors")
public class VendorController {

    @Autowired
    private VendorService vendorService;

    @PostMapping("/createVendor")
    public void createVendor(@RequestBody VendorDTO vendorDTO) {
        vendorService.createVendor(vendorDTO);
    }

    @GetMapping("/{id}")
    public List<ProductDTO> getVendorProducts(@PathVariable Long id) {
        return vendorService.getVendorProducts(id);
    }

    @GetMapping
    public List<VendorDTO> getAllVendors() {
        return vendorService.getAllVendors();
    }

    @PutMapping("/{id}")
    public VendorDTO updateVendor(@PathVariable Long id, @RequestBody VendorDTO vendorDTO) {
        return vendorService.updateVendor(id, vendorDTO);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteVendor(@PathVariable Long id) {
        vendorService.deleteVendor(id);
    }
}
