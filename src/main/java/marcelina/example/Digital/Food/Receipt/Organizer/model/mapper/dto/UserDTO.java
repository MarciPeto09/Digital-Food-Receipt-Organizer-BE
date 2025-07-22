package marcelina.example.Digital.Food.Receipt.Organizer.model.mapper.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
public class UserDTO {

    private Long id;

    private String username;

    private String email;

    private String password;

    private List<ReceiptDTO> receipts;
}
