package marcelina.example.Digital.Food.Receipt.Organizer.service;

import marcelina.example.Digital.Food.Receipt.Organizer.model.Conversation;
import marcelina.example.Digital.Food.Receipt.Organizer.model.mapper.ConversationMapper;
import marcelina.example.Digital.Food.Receipt.Organizer.model.mapper.dto.ConversationDTO;
import marcelina.example.Digital.Food.Receipt.Organizer.repository.ConversationRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConversationService {

    @Autowired
    private ConversationRepo conversationRepository;
    @Autowired
    private ConversationMapper conversationMapper;

    public List<ConversationDTO> getConversationsForUser(Long userId){
        List<Conversation> conversationList = conversationRepository.getConversationsForUser(userId);
        return conversationList.stream()
                .map(c -> conversationMapper.mapToDTO(c))
                .toList();
    }
}
