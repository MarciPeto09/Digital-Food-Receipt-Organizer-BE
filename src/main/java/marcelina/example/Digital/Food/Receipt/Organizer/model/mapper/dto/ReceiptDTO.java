package marcelina.example.Digital.Food.Receipt.Organizer.model.mapper.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class ReceiptDTO {

    private Long id;

    private LocalDateTime uploadDate;

    private LocalDateTime purchaseDate;

    private Double totalAmount;

    private Long vendorId;

    private Long userId;

    private List<ReceiptItemDTO> items;

    private String imageUrl;
}
