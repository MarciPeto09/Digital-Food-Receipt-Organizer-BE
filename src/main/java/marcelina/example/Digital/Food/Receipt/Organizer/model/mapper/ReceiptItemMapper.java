package marcelina.example.Digital.Food.Receipt.Organizer.model.mapper;

import marcelina.example.Digital.Food.Receipt.Organizer.model.Receipt;
import marcelina.example.Digital.Food.Receipt.Organizer.model.ReceiptItem;
import marcelina.example.Digital.Food.Receipt.Organizer.model.mapper.dto.ReceiptDTO;
import marcelina.example.Digital.Food.Receipt.Organizer.model.mapper.dto.ReceiptItemDTO;
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
    private ReceiptMapper receiptMapper;

    public ReceiptItemDTO mapToDto(ReceiptItem receiptItem){

        ReceiptItemDTO receiptItemDTO = new ReceiptItemDTO();

        receiptItemDTO.setId(receiptItem.getId());
        receiptItemDTO.setItemName(receiptItem.getItemName());
        receiptItemDTO.setQuantity(receiptItem.getQuantity());
        receiptItemDTO.setCategory(receiptItem.getCategory());
        receiptItemDTO.setTotalPrice(receiptItem.getTotalPrice());
        receiptItemDTO.setUnitPrice(receiptItem.getUnitPrice());

        if (receiptItem.getReceipt() != null) {
            List<ReceiptDTO> itemDTOs = receiptItem.getReceipt().stream()
                    .map(receiptMapper::mapToDto)
                    .collect(Collectors.toList());
            receiptItemDTO.setReceipt(itemDTOs);
        } else {
            receiptItemDTO.setReceipt(Collections.emptyList());
        }

        return receiptItemDTO;
    }


    public ReceiptItem mapToEntity(ReceiptItemDTO receiptItemDTO){

        ReceiptItem receiptItem = new ReceiptItem();

        receiptItem.setId(receiptItemDTO.getId());
        receiptItem.setItemName(receiptItemDTO.getItemName());
        receiptItem.setQuantity(receiptItemDTO.getQuantity());
        receiptItem.setCategory(receiptItemDTO.getCategory());
        receiptItem.setTotalPrice(receiptItemDTO.getTotalPrice());
        receiptItem.setUnitPrice(receiptItemDTO.getUnitPrice());

        if (receiptItemDTO.getReceipt() != null) {
            List<Receipt> itemDTOs = receiptItemDTO.getReceipt().stream()
                    .map(receiptMapper::mapToEntity)
                    .collect(Collectors.toList());
            receiptItem.setReceipt(itemDTOs);
        } else {
            receiptItem.setReceipt(Collections.emptyList());
        }

        return receiptItem;
    }
}
