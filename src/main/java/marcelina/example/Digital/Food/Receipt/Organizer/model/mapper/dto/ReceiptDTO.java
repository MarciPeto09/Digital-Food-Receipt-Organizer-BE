package marcelina.example.Digital.Food.Receipt.Organizer.model.mapper.dto;

import lombok.Getter;
import lombok.Setter;
import marcelina.example.Digital.Food.Receipt.Organizer.model.ReceiptItem;
import marcelina.example.Digital.Food.Receipt.Organizer.model.User;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class ReceiptDTO {

    private Long id;

    private LocalDateTime uploadDate;

    private LocalDateTime purchaseDate;

    private Double totalAmount;

    private String deliveryAddress;

    private Long userId;

    private List<ReceiptItemDTO> items = new ArrayList<>();

    private String imageUrl;

}
