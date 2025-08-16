package marcelina.example.Digital.Food.Receipt.Organizer.model.mapper.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import marcelina.example.Digital.Food.Receipt.Organizer.model.User;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BasketDTO {

    private Long id;

    private User user;

    List<BasketItemDTO> items;
}
