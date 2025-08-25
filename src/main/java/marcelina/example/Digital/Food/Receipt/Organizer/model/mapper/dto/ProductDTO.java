package marcelina.example.Digital.Food.Receipt.Organizer.model.mapper.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import marcelina.example.Digital.Food.Receipt.Organizer.enumerationClasses.ItemCategory;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {
    private Long id;

    private String productName;

    private int quantity;

    private Double unitPrice;

    private ItemCategory category;

    private List<ReceiptItemDTO> receiptItemsDto;


    private List<BasketItemDTO> basketItemsDto;
}
