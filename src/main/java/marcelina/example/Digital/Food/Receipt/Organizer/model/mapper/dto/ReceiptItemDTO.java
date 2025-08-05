package marcelina.example.Digital.Food.Receipt.Organizer.model.mapper.dto;

import lombok.Getter;
import lombok.Setter;
import marcelina.example.Digital.Food.Receipt.Organizer.model.ItemCategory;

import java.util.List;
@Getter
@Setter
public class ReceiptItemDTO {

    private Long id;

    private String itemName;

    private int quantity;

    private Double unitPrice;

    private ItemCategory category;

    private List<ReceiptDTO> receipt;
}
