package marcelina.example.Digital.Food.Receipt.Organizer.model.mapper;

import marcelina.example.Digital.Food.Receipt.Organizer.model.Receipt;
import marcelina.example.Digital.Food.Receipt.Organizer.model.User;
import marcelina.example.Digital.Food.Receipt.Organizer.model.mapper.dto.ReceiptDTO;
import marcelina.example.Digital.Food.Receipt.Organizer.model.mapper.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserMapper {

    @Autowired
    private ReceiptMapper receiptMapper;

    public UserDTO mapToDto(User user){

        UserDTO userDTO = new UserDTO();

        userDTO.setId(user.getId());
        userDTO.setEmail(user.getEmail());
        userDTO.setPassword(user.getPassword());
        userDTO.setUsername(user.getUsername());

        if (user.getReceipts() != null){
            List<ReceiptDTO> receipts = user.getReceipts().stream()
                    .map(receiptMapper::mapToDto)
                    .toList();
            userDTO.setReceipts(receipts);
        }else{
            userDTO.setReceipts(Collections.emptyList());
        }
        return userDTO;
    }


    public User mapToEntity(UserDTO userDTO){
        User user = new User();
        user.setEmail(userDTO.getEmail());
        user.setPassword(userDTO.getPassword());
        user.setUsername(userDTO.getUsername());

        if (userDTO.getReceipts() != null){
            List<Receipt> receipts = userDTO.getReceipts().stream()
                    .map(receiptMapper::mapToEntity)
                    .toList();
            user.setReceipts(receipts);
        }else{
            user.setReceipts(Collections.emptyList());
        }
        return user;
    }


}