package marcelina.example.Digital.Food.Receipt.Organizer.service;

import marcelina.example.Digital.Food.Receipt.Organizer.model.Receipt;
import marcelina.example.Digital.Food.Receipt.Organizer.model.ReceiptItem;
import marcelina.example.Digital.Food.Receipt.Organizer.model.mapper.ReceiptItemMapper;
import marcelina.example.Digital.Food.Receipt.Organizer.model.mapper.ReceiptMapper;
import marcelina.example.Digital.Food.Receipt.Organizer.model.mapper.dto.ReceiptItemDTO;
import marcelina.example.Digital.Food.Receipt.Organizer.repository.ReceiptItemRepo;
import marcelina.example.Digital.Food.Receipt.Organizer.repository.ReceiptRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReceiptItemsService {

    @Autowired
    private ReceiptItemRepo receiptItemRepository;

    @Autowired
    private ReceiptRepo receiptRepository;

    @Autowired
    private ReceiptItemMapper receiptItemMapper;


    public ReceiptItemDTO addItemToReceipt(Long receiptId, ReceiptItemDTO request){
        Receipt receipt = receiptRepository.findById(receiptId).get();
        receipt.getItems().add(receiptItemMapper.mapToEntity(request));
        return request;
    }

    public List<ReceiptItemDTO> getItemsByReceipt(Long receiptId){
        Receipt receipt = receiptRepository.findById(receiptId).get();
        return receipt.getItems().stream()
                .map(receiptItemMapper::mapToDto)
                .toList();
    }

    public ReceiptItemDTO updateItem(Long itemId, ReceiptItemDTO request){
        ReceiptItemDTO receiptItem = receiptItemMapper.mapToDto(receiptItemRepository.findById(itemId).get());
        receiptItem.setReceipt(request.getReceipt());
        receiptItem.setItemName(receiptItem.getItemName());
        receiptItem.setQuantity(receiptItem.getQuantity());
        receiptItem.setCategory(request.getCategory());
        receiptItem.setTotalPrice(request.getTotalPrice());
        receiptItem.setReceipt(request.getReceipt());

        return receiptItem;
    }

    public void deleteItem(Long itemId){
        receiptRepository.deleteById(itemId);
    }

    public Double calculateTotalForItem(Long itemId){
        ReceiptItem receiptItem = receiptItemRepository.findById(itemId).get();
        return receiptItem.getTotalPrice() * receiptItem.getQuantity();
    }

    public Double calculateTotalForReceipt(Long receiptId){
        Receipt receipt = receiptRepository.findById(receiptId).get();
        List<ReceiptItem> items = receipt.getItems();
         return items.stream()
                 .mapToDouble(i -> i.getQuantity() * i.getUnitPrice())
                 .sum();
    }


}
