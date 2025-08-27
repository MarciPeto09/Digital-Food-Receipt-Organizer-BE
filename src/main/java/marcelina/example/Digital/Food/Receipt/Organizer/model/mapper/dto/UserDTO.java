package marcelina.example.Digital.Food.Receipt.Organizer.model.mapper.dto;

import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import marcelina.example.Digital.Food.Receipt.Organizer.enumerationClasses.Role;
import marcelina.example.Digital.Food.Receipt.Organizer.model.Basket;

import java.util.ArrayList;
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

    private Role role;

    private String photo;

    private List<ReceiptDTO> receipts;

    private Basket basket;

    private String deliveryAddress;

    @OneToMany(mappedBy = "sender")
    private List<MessageDTO> sentMessages = new ArrayList<>();

    @OneToMany(mappedBy = "receiver")
    private List<MessageDTO> receivedMessages = new ArrayList<>();


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
