package marcelina.example.Digital.Food.Receipt.Organizer.service;

import marcelina.example.Digital.Food.Receipt.Organizer.model.Receipt;
import marcelina.example.Digital.Food.Receipt.Organizer.model.User;
import marcelina.example.Digital.Food.Receipt.Organizer.model.Vendor;
import marcelina.example.Digital.Food.Receipt.Organizer.model.mapper.ReceiptMapper;
import marcelina.example.Digital.Food.Receipt.Organizer.model.mapper.dto.ReceiptDTO;
import marcelina.example.Digital.Food.Receipt.Organizer.repository.ReceiptRepo;
import marcelina.example.Digital.Food.Receipt.Organizer.repository.UserRepo;
import marcelina.example.Digital.Food.Receipt.Organizer.repository.VendorRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;

@Service
public class ReceiptService {

    @Autowired
    private ReceiptRepo receiptRepository;

    @Autowired
    private UserRepo userRepository;

    @Autowired
    private VendorRepo vendorRepository;

    @Autowired
    private ReceiptMapper receiptMapper;


    public void createReceipt(ReceiptDTO receiptDTO, Long userId){
        Receipt receipt = receiptMapper.mapToEntity(receiptDTO);
        receipt.setUser(userRepository.findById(userId).get());
        receiptRepository.save(receipt);
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

    public List<ReceiptDTO> getReceiptsByVendor(Long userId, Long vendorId){
        Vendor vendor = vendorRepository.findById(vendorId).get();
        return vendor.getReceipts().stream()
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
