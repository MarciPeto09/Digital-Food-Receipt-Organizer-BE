package marcelina.example.Digital.Food.Receipt.Organizer.controller;

import marcelina.example.Digital.Food.Receipt.Organizer.model.mapper.dto.ReceiptItemDTO;
import marcelina.example.Digital.Food.Receipt.Organizer.service.ReceiptItemsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/items")
public class ReceiptItemController {

    @Autowired
    private ReceiptItemsService receiptItemsService;


    @GetMapping
    public List<ReceiptItemDTO> getAllItems(){
        return receiptItemsService.getAllItems();
    }

    @PostMapping("/receipt/{receiptId}")
    public ReceiptItemDTO addItemToReceipt(@PathVariable Long receiptId, @RequestBody ReceiptItemDTO itemDTO) {
        return receiptItemsService.addItemToReceipt(receiptId, itemDTO);
    }


    @GetMapping("/receipt/{receiptId}")
    public List<ReceiptItemDTO> getItemsByReceipt(@PathVariable Long receiptId) {
        return receiptItemsService.getItemsByReceipt(receiptId);
    }


    @PutMapping("/{itemId}")
    public ReceiptItemDTO updateItem(@PathVariable Long itemId, @RequestBody ReceiptItemDTO itemDTO) {
        return receiptItemsService.updateItem(itemId, itemDTO);
    }


    @DeleteMapping("/{itemId}")
    public void deleteItem(@PathVariable Long itemId) {
        receiptItemsService.deleteItem(itemId);
    }


    @GetMapping("/receipt/{receiptId}/total")
    public Double calculateTotalForReceipt(@PathVariable Long receiptId) {
        return receiptItemsService.calculateTotalForReceipt(receiptId);
    }
}
