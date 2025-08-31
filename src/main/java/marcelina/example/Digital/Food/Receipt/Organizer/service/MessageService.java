package marcelina.example.Digital.Food.Receipt.Organizer.service;

import marcelina.example.Digital.Food.Receipt.Organizer.model.Conversation;
import marcelina.example.Digital.Food.Receipt.Organizer.model.Message;
import marcelina.example.Digital.Food.Receipt.Organizer.model.MessageRequest;
import marcelina.example.Digital.Food.Receipt.Organizer.model.User;
import marcelina.example.Digital.Food.Receipt.Organizer.model.mapper.MessageMapper;
import marcelina.example.Digital.Food.Receipt.Organizer.model.mapper.dto.MessageDTO;
import marcelina.example.Digital.Food.Receipt.Organizer.repository.ConversationRepo;
import marcelina.example.Digital.Food.Receipt.Organizer.repository.MessageRepo;
import marcelina.example.Digital.Food.Receipt.Organizer.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class MessageService {

    @Autowired
    private UserRepo userRepository;

    @Autowired
    private MessageRepo messageRepository;

    @Autowired
    private MessageMapper messageMapper;

    @Autowired
    private ConversationRepo conversationRepository;

   public MessageDTO sendMessage(MessageRequest messageRequest){
       Message message = new Message();
       User sender = userRepository.findById(messageRequest.getSenderId()).get();
       message.setSender(sender);
       User receiver = userRepository.findById(messageRequest.getRecipientId()).get();
       message.setReceiver(receiver);
       message.setContent(messageRequest.getContent());
       message.setDateTime(LocalDateTime.now());
       message.setSeen(false);
       Conversation conversation = conversationRepository
               .findConversationBetweenUsers(messageRequest.getSenderId(), messageRequest.getRecipientId())
               .orElseGet(() -> {
                   Conversation newConv = new Conversation();
                   newConv.getParticipants().add(sender);
                   newConv.getParticipants().add(receiver);
                   return conversationRepository.save(newConv);
               });
       message.setConversation(conversation);
       messageRepository.save(message);
       return messageMapper.mapToDTO(message);
   }

   public List<MessageDTO> getMessagesBetweenUsers(Long user1Id,Long user2Id){
       List<Message> messageList = messageRepository.findConversationBetweenUsers(user1Id,user2Id);
       return messageList.stream()
               .map(m -> messageMapper.mapToDTO(m))
               .toList();
   }

    public void markMessagesAsSeen(Long senderId, Long receiverId) {
        List<Message> messages = messageRepository.findBySenderIdAndRecipientIdAndSeenFalse(senderId, receiverId);
        for (Message msg : messages) {
            msg.setSeen(true);
        }
        messageRepository.saveAll(messages);
    }

    public int countUnseenMessagesPerSender(Long senderId, Long receiverId){
       return messageRepository.countUnseenMessagesPerSender(senderId,receiverId);
    }

}
