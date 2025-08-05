package marcelina.example.Digital.Food.Receipt.Organizer.controller;

import marcelina.example.Digital.Food.Receipt.Organizer.model.mapper.dto.ReceiptDTO;
import marcelina.example.Digital.Food.Receipt.Organizer.service.ReceiptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/receipts")
@CrossOrigin(origins = "http://localhost:3000")
public class ReceiptController {

    @Autowired
    private ReceiptService receiptService;


    @PostMapping("/upload/user/{userId}")
    public void createReceipt(@RequestBody ReceiptDTO receiptDTO, @PathVariable Long userId) {
        receiptService.createReceipt(receiptDTO, userId);
    }


    @GetMapping("/{receiptId}")
    public ReceiptDTO getReceiptById(@PathVariable Long receiptId) {
        return receiptService.getReceiptById(receiptId);
    }


    @GetMapping("/user/{userId}")
    public List<ReceiptDTO> getReceiptsByUser(@PathVariable Long userId) {
        return receiptService.getReceiptsByUser(userId);
    }


    @GetMapping("/user/{userId}/vendor/{vendorId}")
    public List<ReceiptDTO> getReceiptsByVendor(@PathVariable Long userId, @PathVariable Long vendorId) {
        return receiptService.getReceiptsByVendor(userId, vendorId);
    }


    @GetMapping("/user/{userId}/search")
    public List<ReceiptDTO> searchReceipts(@PathVariable Long userId, @RequestParam String keyword) {
        return receiptService.searchReceipts(userId, keyword);
    }


    @PutMapping("/{receiptId}")
    public ReceiptDTO updateReceipt(@PathVariable Long receiptId, @RequestBody ReceiptDTO updatedReceipt) {
        return receiptService.updateReceipt(receiptId, updatedReceipt);
    }


    @DeleteMapping("/{receiptId}")
    public void deleteReceipt(@PathVariable Long receiptId) {
        receiptService.deleteReceipt(receiptId);
    }
}
