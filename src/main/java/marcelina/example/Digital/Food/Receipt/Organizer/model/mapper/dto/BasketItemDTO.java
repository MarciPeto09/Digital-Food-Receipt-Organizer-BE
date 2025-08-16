package marcelina.example.Digital.Food.Receipt.Organizer.model.mapper.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import marcelina.example.Digital.Food.Receipt.Organizer.model.Basket;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BasketItemDTO {

    private Long id;

    private ProductDTO productDTO;

    private Basket basket;

    private int quantity;
}
