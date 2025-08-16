package marcelina.example.Digital.Food.Receipt.Organizer.service;

import marcelina.example.Digital.Food.Receipt.Organizer.model.Receipt;
import marcelina.example.Digital.Food.Receipt.Organizer.model.ReceiptItem;
import marcelina.example.Digital.Food.Receipt.Organizer.model.mapper.ReceiptItemMapper;
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


    public List<ReceiptItemDTO> getAllItems(){
        List<ReceiptItem> listOfAllItems = receiptItemRepository.findAll();
        return listOfAllItems.stream()
                .map(i -> receiptItemMapper.mapToDto(i))
                .toList();
    }

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
        receiptItem.setReceiptId(request.getReceiptId());
        receiptItem.setItemName(receiptItem.getItemName());
        receiptItem.setQuantity(receiptItem.getQuantity());
        receiptItem.setCategory(request.getCategory());

        return receiptItem;
    }

    public void deleteItem(Long itemId){
        receiptRepository.deleteById(itemId);
    }

    public Double calculateTotalForReceipt(Long receiptId){
        Receipt receipt = receiptRepository.findById(receiptId).get();
        List<ReceiptItem> items = receipt.getItems();
         return items.stream()
                 .mapToDouble(i -> i.getQuantity() * i.getUnitPrice())
                 .sum();
    }


}
