package marcelina.example.Digital.Food.Receipt.Organizer.model.mapper.dto;


import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
public class VendorDTO {
    private Long id;

    private String name;

    private String location;

    private List<ReceiptDTO> receipts;
}
