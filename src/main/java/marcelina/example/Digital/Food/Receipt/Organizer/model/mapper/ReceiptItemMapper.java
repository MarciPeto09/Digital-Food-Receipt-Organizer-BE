package marcelina.example.Digital.Food.Receipt.Organizer.model.mapper;

import marcelina.example.Digital.Food.Receipt.Organizer.model.Receipt;
import marcelina.example.Digital.Food.Receipt.Organizer.model.ReceiptItem;
import marcelina.example.Digital.Food.Receipt.Organizer.model.mapper.dto.ReceiptDTO;
import marcelina.example.Digital.Food.Receipt.Organizer.model.mapper.dto.ReceiptItemDTO;
import marcelina.example.Digital.Food.Receipt.Organizer.repository.ReceiptRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ReceiptItemMapper {
    @Autowired
    @Lazy
    private ReceiptRepo receiptRepository;

    @Autowired
    @Lazy
    private ProductMapper productMapper;

    public ReceiptItemDTO mapToDto(ReceiptItem receiptItem){

        ReceiptItemDTO receiptItemDTO = new ReceiptItemDTO();

        receiptItemDTO.setId(receiptItem.getId());
        receiptItemDTO.setItemName(receiptItem.getItemName());
        receiptItemDTO.setQuantity(receiptItem.getQuantity());
        receiptItemDTO.setCategory(receiptItem.getCategory());
        receiptItemDTO.setUnitPrice(receiptItem.getUnitPrice());
        receiptItemDTO.setTotalPrice(receiptItem.getTotalPrice());
        receiptItemDTO.setProductDTO(productMapper.maptoDto(receiptItem.getProduct()));
        if (receiptItem.getReceipt() != null) {
            receiptItemDTO.setReceiptId(receiptItem.getReceipt().getId());
        }
        return receiptItemDTO;
    }


    public ReceiptItem mapToEntity(ReceiptItemDTO receiptItemDTO){

        ReceiptItem receiptItem = new ReceiptItem();

        receiptItem.setId(receiptItemDTO.getId());
        receiptItem.setItemName(receiptItemDTO.getItemName());
        receiptItem.setQuantity(receiptItemDTO.getQuantity());
        receiptItem.setCategory(receiptItemDTO.getCategory());
        receiptItem.setUnitPrice(receiptItemDTO.getUnitPrice());
        receiptItem.setTotalPrice(receiptItem.getTotalPrice());
        receiptItem.setProduct(productMapper.mapToEntity(receiptItemDTO.getProductDTO()));
        if (receiptItemDTO.getReceiptId() != null) {
            Receipt receipt = receiptRepository.findById(receiptItemDTO.getReceiptId())
                    .orElseThrow(() -> new RuntimeException("Receipt with id " + receiptItemDTO.getReceiptId() + " does not exist!"));
            receiptItem.setReceipt(receipt);
        }
        return receiptItem;
    }
}
