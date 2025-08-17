package marcelina.example.Digital.Food.Receipt.Organizer.model.mapper;

import marcelina.example.Digital.Food.Receipt.Organizer.model.Conversation;
import marcelina.example.Digital.Food.Receipt.Organizer.model.Message;
import marcelina.example.Digital.Food.Receipt.Organizer.model.User;
import marcelina.example.Digital.Food.Receipt.Organizer.model.mapper.dto.ConversationDTO;
import marcelina.example.Digital.Food.Receipt.Organizer.model.mapper.dto.MessageDTO;
import marcelina.example.Digital.Food.Receipt.Organizer.model.mapper.dto.UserDTO;
import marcelina.example.Digital.Food.Receipt.Organizer.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ConversationMapper {
    @Autowired
    public UserRepo userRepository;

    public ConversationDTO mapToDTO(Conversation conversation){
        ConversationDTO conversationDTO = new ConversationDTO();
        conversationDTO.setId(conversation.getId());

        if(conversation.getParticipants() != null){
            List<User> userList = conversation.getParticipants();
            for(User u : userList){
                UserDTO userDTO = new UserDTO();
                userDTO.setId(u.getId());
                userDTO.setPhoto(u.getPhotoUrl());
                userDTO.setBasket(u.getBasket());
                userDTO.setEmail(u.getEmail());
                userDTO.setUsername(u.getUsername());
                conversationDTO.getParticipants().add(userDTO);
            }
        }

        if(conversation.getMessages() != null){
            List<Message> list = conversation.getMessages();
            for(Message m : list){
                MessageDTO messageDTO = new MessageDTO();
                messageDTO.setId(m.getId());
                messageDTO.setSeen(m.isSeen());
                messageDTO.setContent(m.getContent());
                messageDTO.setReceiverId(m.getReceiver().getId());
                messageDTO.setSenderId(m.getSender().getId());
                messageDTO.setDateTime(m.getDateTime());
                conversationDTO.getMessages().add(messageDTO);
            }
        }
        return conversationDTO;
    }


    public Conversation mapToEntity(ConversationDTO conversationDTO){
        Conversation conversation = new Conversation();
        conversation.setId(conversationDTO.getId());

        if(conversationDTO.getParticipants() != null){
            List<UserDTO> userList = conversationDTO.getParticipants();
            for(UserDTO u : userList){
                User user = new User();
                user.setId(u.getId());
                user.setPhotoUrl(u.getPhoto());
                user.setBasket(u.getBasket());
                user.setEmail(u.getEmail());
                user.setUsername(u.getUsername());
                conversation.getParticipants().add(user);
            }
        }

        if(conversationDTO.getMessages() != null){
            List<MessageDTO> list = conversationDTO.getMessages();
            for(MessageDTO m : list){
                Message message = new Message();
                message.setId(m.getId());
                message.setSeen(m.isSeen());
                message.setContent(m.getContent());
                User receiver = userRepository.findById(m.getReceiverId()).get();
                message.setReceiver(receiver);
                User sender = userRepository.findById(m.getSenderId()).get();
                message.setSender(sender);
                message.setDateTime(m.getDateTime());
                conversation.getMessages().add(message);
            }
        }
        return conversation;
    }
}
