package marcelina.example.Digital.Food.Receipt.Organizer.model.mapper.dto;


import lombok.Getter;
import lombok.Setter;
import marcelina.example.Digital.Food.Receipt.Organizer.model.Product;

import java.util.ArrayList;
import java.util.List;
@Getter
@Setter
public class VendorDTO {
    private Long id;

    private String name;

    private String location;

    private List<ProductDTO> productDTOs = new ArrayList<>();

    private List<ReceiptDTO> receipts;
}
