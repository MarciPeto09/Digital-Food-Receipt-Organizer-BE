package marcelina.example.Digital.Food.Receipt.Organizer.model.mapper;

import marcelina.example.Digital.Food.Receipt.Organizer.model.Message;
import marcelina.example.Digital.Food.Receipt.Organizer.model.Receipt;
import marcelina.example.Digital.Food.Receipt.Organizer.model.User;
import marcelina.example.Digital.Food.Receipt.Organizer.model.mapper.dto.MessageDTO;
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

    @Autowired
    private MessageMapper messageMapper;

    public UserDTO mapToDto(User user){

        UserDTO userDTO = new UserDTO();

        userDTO.setId(user.getId());
        userDTO.setEmail(user.getEmail());
        userDTO.setPassword(user.getPassword());
        userDTO.setUsername(user.getUsername());
        userDTO.setPhoto(user.getPhotoUrl());
        userDTO.setBasket(user.getBasket());

        if (user.getReceipts() != null){
            List<ReceiptDTO> receipts = user.getReceipts().stream()
                    .map(receiptMapper::mapToDto)
                    .toList();
            userDTO.setReceipts(receipts);
        }else{
            userDTO.setReceipts(Collections.emptyList());
        }

        if (user.getSentMessages() != null){
            List<MessageDTO> messageDTOList = user.getSentMessages().stream()
                    .map(messageMapper::mapToDTO)
                    .toList();
            userDTO.setSentMessages(messageDTOList);
        }else{
            userDTO.setSentMessages(Collections.emptyList());
        }

        if (user.getReceivedMessages() != null){
            List<MessageDTO> messageDTOList = user.getReceivedMessages().stream()
                    .map(messageMapper::mapToDTO)
                    .toList();
            userDTO.setReceivedMessages(messageDTOList);
        }else{
            userDTO.setReceivedMessages(Collections.emptyList());
        }
        return userDTO;
    }


    public User mapToEntity(UserDTO userDTO){
        User user = new User();
        user.setEmail(userDTO.getEmail());
        user.setPassword(userDTO.getPassword());
        user.setUsername(userDTO.getUsername());
        user.setPhotoUrl(userDTO.getPhoto());
        user.setBasket(userDTO.getBasket());

        if (userDTO.getReceipts() != null){
            List<Receipt> receipts = userDTO.getReceipts().stream()
                    .map(receiptMapper::mapToEntity)
                    .toList();
            user.setReceipts(receipts);
        }else{
            user.setReceipts(Collections.emptyList());
        }

        if (userDTO.getReceivedMessages() != null){
            List<Message> messageList = userDTO.getReceivedMessages().stream()
                    .map(messageMapper::mapToEntity)
                    .toList();
            user.setReceivedMessages(messageList);
        }else{
            user.setReceivedMessages(Collections.emptyList());
        }

        if (userDTO.getSentMessages() != null){
            List<Message> messageList = userDTO.getSentMessages().stream()
                    .map(messageMapper::mapToEntity)
                    .toList();
            user.setSentMessages(messageList);
        }else{
            user.setSentMessages(Collections.emptyList());
        }
        return user;
    }


}