package marcelina.example.Digital.Food.Receipt.Organizer.controller;

import marcelina.example.Digital.Food.Receipt.Organizer.model.MessageRequest;
import marcelina.example.Digital.Food.Receipt.Organizer.model.mapper.dto.MessageDTO;
import marcelina.example.Digital.Food.Receipt.Organizer.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/message")
@CrossOrigin(origins = "http://localhost:3000")
public class MessageController {

    @Autowired
    private MessageService messageService;

    @GetMapping("/getMessageBetweenUser")
    public List<MessageDTO> getMessageBetweenUser(@RequestParam Long senderId,@RequestParam Long receiverId ){
        return messageService.getMessagesBetweenUsers(senderId,receiverId);
    }

    @PostMapping("/send")
    public MessageDTO sendMessage(@RequestBody MessageRequest messageRequest){
        return messageService.sendMessage(messageRequest);
    }
}
