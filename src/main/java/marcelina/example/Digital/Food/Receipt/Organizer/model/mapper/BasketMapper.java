package marcelina.example.Digital.Food.Receipt.Organizer.model.mapper;

import marcelina.example.Digital.Food.Receipt.Organizer.model.Basket;
import marcelina.example.Digital.Food.Receipt.Organizer.model.BasketItem;
import marcelina.example.Digital.Food.Receipt.Organizer.model.mapper.dto.BasketDTO;
import marcelina.example.Digital.Food.Receipt.Organizer.model.mapper.dto.BasketItemDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
@Component
public class BasketMapper {

    @Autowired
    @Lazy
    private BasketItemMapper basketItemMapper;

    public BasketDTO mapToDto(Basket basket){
        BasketDTO basketDTO = new BasketDTO();

        basketDTO.setId(basket.getId());
        basketDTO.setUser(basket.getUser());

        if(basket.getItems() != null){
            List<BasketItemDTO> listOfBasketItems = basket.getItems().stream()
                    .map(i -> basketItemMapper.mapToDto(i))
                    .toList();
            basketDTO.setItems(listOfBasketItems);
        }else{
            basketDTO.setItems(Collections.emptyList());
        }
        return basketDTO;
    }

    public Basket mapToEntity (BasketDTO basketDTO){
        Basket basket = new Basket();

        basket.setId(basketDTO.getId());
        basket.setUser(basketDTO.getUser());

        if(basketDTO.getItems() != null){
            List<BasketItem> listOfBasketItems = basketDTO.getItems().stream()
                    .map(i -> basketItemMapper.mapToEntity(i))
                    .toList();
            basket.setItems(listOfBasketItems);
        }else{
            basket.setItems(Collections.emptyList());
        }
        return basket;
    }
}
