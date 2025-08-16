package marcelina.example.Digital.Food.Receipt.Organizer.service;

import marcelina.example.Digital.Food.Receipt.Organizer.model.*;
import marcelina.example.Digital.Food.Receipt.Organizer.model.mapper.ReceiptMapper;
import marcelina.example.Digital.Food.Receipt.Organizer.model.mapper.dto.ReceiptDTO;
import marcelina.example.Digital.Food.Receipt.Organizer.model.mapper.dto.ReceiptItemDTO;
import marcelina.example.Digital.Food.Receipt.Organizer.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Service
public class ReceiptService {

    @Autowired
    private ReceiptRepo receiptRepository;

    @Autowired
    private UserRepo userRepository;

    @Autowired
    private BasketRepo basketRepository;

    @Autowired
    private ReceiptItemRepo receiptItemRepository;

    @Autowired
    private VendorRepo vendorRepository;

    @Autowired
    private ReceiptMapper receiptMapper;

    public ReceiptDTO createReceiptFromBasket(Long basketId) {
        Basket basket = basketRepository.findById(basketId)
                .orElseThrow(() -> new RuntimeException("Basket not found"));

        if (basket.getItems().isEmpty()) {
            throw new RuntimeException("Basket is empty");
        }

        Receipt receipt = new Receipt();
        receipt.setUploadDate(LocalDateTime.now());
        receipt.setUser(basket.getUser());
        receipt.setItems(new ArrayList<>());

        double total = 0;

        for (BasketItem basketItem : basket.getItems()) {
            ReceiptItem receiptItem = new ReceiptItem();
            receiptItem.setItemName(basketItem.getProduct().getProductName());
            receiptItem.setQuantity(basketItem.getQuantity());
            receiptItem.setUnitPrice(basketItem.getProduct().getUnitPrice());

            double itemTotal = basketItem.getQuantity() * basketItem.getProduct().getUnitPrice();
            receiptItem.setTotalPrice(itemTotal);
            total += itemTotal;

            receiptItem.setReceipt(receipt);
            receipt.getItems().add(receiptItem);
        }

        receipt.setTotalAmount(total);
        return receiptMapper.mapToDto(receipt);
    }

    public void saveReceipt(Long basketId) {
        Receipt receipt = receiptMapper.mapToEntity(createReceiptFromBasket(basketId));
        Basket basket = basketRepository.findById(basketId)
                .orElseThrow(() -> new RuntimeException("There is no basket with id " + basketId));

        receipt.setPurchaseDate(LocalDateTime.now());
        receiptRepository.save(receipt);

        for (BasketItem basketItem : basket.getItems()) {
            ReceiptItem receiptItem = new ReceiptItem();
            receiptItem.setItemName(basketItem.getProduct().getProductName());
            receiptItem.setQuantity(basketItem.getQuantity());
            receiptItem.setUnitPrice(basketItem.getProduct().getUnitPrice());
            receiptItem.setReceipt(receipt);

            receiptItemRepository.save(receiptItem);
            receipt.setItems(new ArrayList<>());
            receipt.getItems().add(receiptItem);
        }

        basket.getItems().clear();
        basketRepository.save(basket);
    }



    public ReceiptDTO getReceiptById(Long receiptId){
        return receiptMapper.mapToDto(receiptRepository.findById(receiptId).get());
    }


    public List<ReceiptDTO> getReceiptsByUser(Long userId){
        User user = userRepository.findById(userId).get();
        return user.getReceipts().stream()
                .map(receiptMapper::mapToDto)
                .toList();

    }

    public List<ReceiptDTO> searchReceipts(Long userId, String keyword){
        User user = userRepository.findById(userId).get();
        return user.getReceipts().stream()
                .filter(r -> r.getItems().stream().anyMatch(item -> item.getItemName().toLowerCase().contains(keyword.toLowerCase())))
                .map(receiptMapper::mapToDto)
                .toList();
    }

    public ReceiptDTO updateReceipt(Long receiptId, ReceiptDTO updatedRequest){
        ReceiptDTO receiptDTO = receiptMapper.mapToDto(receiptRepository.findById(receiptId).get());
        receiptDTO.setId(updatedRequest.getId());
        receiptDTO.setUserId(updatedRequest.getUserId());
        receiptDTO.setItems(updatedRequest.getItems());
        receiptDTO.setPurchaseDate(updatedRequest.getPurchaseDate());
        receiptDTO.setUploadDate(updatedRequest.getUploadDate());
        receiptDTO.setTotalAmount(updatedRequest.getTotalAmount());
        receiptDTO.setImageUrl(updatedRequest.getImageUrl());

        return receiptDTO;
    }

    public void deleteReceipt(Long receiptId){
        receiptRepository.deleteById(receiptId);
    }

}
