package marcelina.example.Digital.Food.Receipt.Organizer.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MessageRequest {
    private Long senderId;
    private Long recipientId;
    private String content;
}

