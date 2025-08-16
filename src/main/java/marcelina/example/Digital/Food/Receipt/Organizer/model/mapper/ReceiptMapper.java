package marcelina.example.Digital.Food.Receipt.Organizer.model.mapper;

import marcelina.example.Digital.Food.Receipt.Organizer.model.Receipt;
import marcelina.example.Digital.Food.Receipt.Organizer.model.ReceiptItem;
import marcelina.example.Digital.Food.Receipt.Organizer.model.mapper.dto.ReceiptDTO;
import marcelina.example.Digital.Food.Receipt.Organizer.model.mapper.dto.ReceiptItemDTO;
import marcelina.example.Digital.Food.Receipt.Organizer.repository.UserRepo;
import marcelina.example.Digital.Food.Receipt.Organizer.repository.VendorRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ReceiptMapper {

    @Autowired
    private ReceiptItemMapper receiptItemMapper;
    @Autowired
    private UserRepo userRepository;
    @Autowired
    private VendorRepo vendorRepository;

    public ReceiptDTO mapToDto(Receipt receipt){

        ReceiptDTO receiptDTO = new ReceiptDTO();

        receiptDTO.setId(receipt.getId());
        receiptDTO.setImageUrl(receipt.getImageUrl());
        receiptDTO.setUserId(receipt.getUser().getId());
        receiptDTO.setTotalAmount(receipt.getTotalAmount());
        receiptDTO.setPurchaseDate(receipt.getPurchaseDate());
        receiptDTO.setUploadDate(receipt.getUploadDate());
        if(receipt.getVendor() != null ){
            receiptDTO.setVendorId(receipt.getVendor().getId());
        }

        if (receipt.getItems() != null) {
            List<ReceiptItemDTO> itemDTOs = receipt.getItems().stream()
                    .map(receiptItemMapper::mapToDto)
                    .collect(Collectors.toList());
            receiptDTO.setItems(itemDTOs);
        } else {
            receiptDTO.setItems(Collections.emptyList());
        }

        return receiptDTO;
    }


    public Receipt mapToEntity(ReceiptDTO receiptDTO){

        Receipt receipt = new Receipt();

        receipt.setId(receiptDTO.getId());
        receipt.setImageUrl(receiptDTO.getImageUrl());
        receipt.setPurchaseDate(receiptDTO.getPurchaseDate());
        receipt.setTotalAmount(receiptDTO.getTotalAmount());
        receipt.setUser(userRepository.findById(receiptDTO.getUserId()).get());
        if(receiptDTO.getVendorId() != null){
            receipt.setVendor(vendorRepository.findById(receiptDTO.getVendorId()).get());
        }


        if(receiptDTO.getItems() != null){
            List<ReceiptItem> items = receiptDTO.getItems().stream()
                    .map(receiptItemMapper::mapToEntity)
                    .toList();
            receipt.setItems(items);
        }else{
            receipt.setItems(Collections.emptyList());
        }

        return receipt;
    }
}
