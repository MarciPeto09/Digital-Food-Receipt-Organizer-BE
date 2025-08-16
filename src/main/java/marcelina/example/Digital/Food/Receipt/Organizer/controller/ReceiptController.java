package marcelina.example.Digital.Food.Receipt.Organizer.controller;

import marcelina.example.Digital.Food.Receipt.Organizer.model.Receipt;
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

    @PostMapping("/from-basket/{basketId}")
    public ReceiptDTO createReceiptFromBasket(@PathVariable Long basketId) {
        return receiptService.createReceiptFromBasket(basketId);
    }

    @PostMapping("/saveReceipt/{basketId}")
    public void saveReceipt (@PathVariable Long basketId){
        receiptService.saveReceipt( basketId);
    }

    @GetMapping("/get")
    public ReceiptDTO getReceiptById(@RequestParam Long receiptId){
        return receiptService.getReceiptById(receiptId);
    }

    @GetMapping("/user/{userId}")
    public List<ReceiptDTO> getReceiptsByUser(@PathVariable Long userId) {
        return receiptService.getReceiptsByUser(userId);
    }

    @GetMapping("/user/{userId}/search")
    public List<ReceiptDTO> searchReceipts(@PathVariable Long userId, @RequestParam String keyword) {
        return receiptService.searchReceipts(userId, keyword);
    }

    @DeleteMapping("/{receiptId}")
    public void deleteReceipt(@PathVariable Long receiptId) {
        receiptService.deleteReceipt(receiptId);
    }
}
