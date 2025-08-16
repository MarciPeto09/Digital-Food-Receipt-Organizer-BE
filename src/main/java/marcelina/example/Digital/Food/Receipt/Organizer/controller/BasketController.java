package marcelina.example.Digital.Food.Receipt.Organizer.controller;

import marcelina.example.Digital.Food.Receipt.Organizer.model.Basket;
import marcelina.example.Digital.Food.Receipt.Organizer.model.BasketItem;
import marcelina.example.Digital.Food.Receipt.Organizer.model.mapper.dto.BasketDTO;
import marcelina.example.Digital.Food.Receipt.Organizer.model.mapper.dto.BasketItemDTO;
import marcelina.example.Digital.Food.Receipt.Organizer.service.BasketItemService;
import marcelina.example.Digital.Food.Receipt.Organizer.service.BasketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/basket")
@CrossOrigin(origins = "http://localhost:3000")
public class BasketController {

    @Autowired
    private BasketService basketService;

    @Autowired
    private BasketItemService basketItemService;

    @GetMapping("/{basketId}")
    public BasketDTO getBasket(@PathVariable Long basketId){
        return basketService.getBasket(basketId);
    }

    @PostMapping("/addBasketItemToBasket/{basketId}")
    public BasketDTO addBasketItemToBasket(@PathVariable Long basketId, @RequestParam Long productId, @RequestParam int itemQuantity){
        return basketService.addBasketItemToBasket(basketId,productId,itemQuantity);
    }

    @PostMapping("/addQuantity")
    public void addQuantity(@RequestParam Long basketId,@RequestParam Long basketItemId){
        basketService.addQuantity(basketId,basketItemId);
    }

    @PostMapping("/decrementQuantity")
    public void decrementQuantity(@RequestParam Long basketId,@RequestParam Long basketItemId){
        basketService.decrementQuantity(basketId,basketItemId);
    }

    @DeleteMapping("/deleteBasketItemFromBasket")
    public void deleteBasketItemFromBasket(@RequestParam Long basketId,@RequestParam Long basketItemId){
        basketService.deleteBasketItemFromBasket(basketId,basketItemId);
    }

    @GetMapping("/total")
    public Double getTotal(@RequestParam Long basketId){
        return basketService.getTotal(basketId);
    }
}
