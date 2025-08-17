package marcelina.example.Digital.Food.Receipt.Organizer.controller;

import marcelina.example.Digital.Food.Receipt.Organizer.model.mapper.dto.ConversationDTO;
import marcelina.example.Digital.Food.Receipt.Organizer.service.ConversationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/conversation")
@CrossOrigin(origins = "http://localhost:3000")
public class ConversationController {

    @Autowired
    private ConversationService conversationService;

    @GetMapping()
    public List<ConversationDTO> getConversationsForUser(@RequestParam  Long userId){
        return conversationService.getConversationsForUser(userId);
    }
}
