package marcelina.example.Digital.Food.Receipt.Organizer.model.mapper.dto;

import lombok.Getter;
import lombok.Setter;
import marcelina.example.Digital.Food.Receipt.Organizer.enumerationClasses.ItemCategory;

@Getter
@Setter
public class ReceiptItemDTO {

    private Long id;

    private String itemName;

    private int quantity;

    private Double unitPrice;

    private Double totalPrice;

    private ProductDTO productDTO;

    private ItemCategory category;

    private Long receiptId;
}
