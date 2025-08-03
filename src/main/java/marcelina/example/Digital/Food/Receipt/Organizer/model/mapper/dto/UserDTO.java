package marcelina.example.Digital.Food.Receipt.Organizer.model.mapper.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

    private Long id;

    private String username;

    private String email;

    private String password;

    private String photo;

    private List<ReceiptDTO> receipts;


    public UserDTO(Long id, String username, String email) {
        this.id = id;
        this.username = username;
        this.email = email;
    }

    public UserDTO(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }
}
