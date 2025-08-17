package marcelina.example.Digital.Food.Receipt.Organizer.model.mapper;

import marcelina.example.Digital.Food.Receipt.Organizer.model.Message;
import marcelina.example.Digital.Food.Receipt.Organizer.model.User;
import marcelina.example.Digital.Food.Receipt.Organizer.model.mapper.dto.MessageDTO;
import marcelina.example.Digital.Food.Receipt.Organizer.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MessageMapper {

    @Autowired
    public UserRepo userRepository;

    public MessageDTO mapToDTO(Message message){
        MessageDTO messageDTO = new MessageDTO();
        messageDTO.setId(message.getId());
        messageDTO.setSeen(message.isSeen());
        messageDTO.setContent(message.getContent());
        messageDTO.setDateTime(message.getDateTime());
        messageDTO.setSenderId(message.getSender().getId());
        messageDTO.setReceiverId(message.getReceiver().getId());
        return messageDTO;
    }

    public Message mapToEntity(MessageDTO messageDTO){
        Message message = new Message();
        message.setId(messageDTO.getId());
        message.setContent(messageDTO.getContent());
        message.setSeen(messageDTO.isSeen());
        User receiver = userRepository.findById(messageDTO.getReceiverId()).get();
        message.setReceiver(receiver);
        User sender = userRepository.findById(messageDTO.getSenderId()).get();
        message.setSender(sender);
        return message;
    }
}
