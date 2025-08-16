package marcelina.example.Digital.Food.Receipt.Organizer.service;

import marcelina.example.Digital.Food.Receipt.Organizer.model.Basket;
import marcelina.example.Digital.Food.Receipt.Organizer.model.BasketItem;
import marcelina.example.Digital.Food.Receipt.Organizer.model.mapper.BasketItemMapper;
import marcelina.example.Digital.Food.Receipt.Organizer.model.mapper.dto.BasketItemDTO;
import marcelina.example.Digital.Food.Receipt.Organizer.repository.BasketItemRepo;
import marcelina.example.Digital.Food.Receipt.Organizer.repository.BasketRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BasketItemService {
    @Autowired
    private BasketRepo basketRepository;

    @Autowired
    private BasketItemRepo basketItemRepository;

    @Autowired
    private BasketItemMapper basketItemMapper;


    public List<BasketItemDTO> getBasketItemsForBasket(Long basketId){
        Basket basket = basketRepository.findById(basketId).orElseThrow(() -> new RuntimeException("No basket found with this id!"));
        List<BasketItem> basketItemList = basket.getItems();
        return basketItemList.stream()
                .map(i -> basketItemMapper.mapToDto(i))
                .toList();
    }

}
