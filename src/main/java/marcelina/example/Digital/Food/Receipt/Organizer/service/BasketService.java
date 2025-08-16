package marcelina.example.Digital.Food.Receipt.Organizer.service;


import marcelina.example.Digital.Food.Receipt.Organizer.model.Basket;
import marcelina.example.Digital.Food.Receipt.Organizer.model.BasketItem;
import marcelina.example.Digital.Food.Receipt.Organizer.model.Product;
import marcelina.example.Digital.Food.Receipt.Organizer.model.mapper.BasketMapper;
import marcelina.example.Digital.Food.Receipt.Organizer.model.mapper.dto.BasketDTO;
import marcelina.example.Digital.Food.Receipt.Organizer.repository.BasketItemRepo;
import marcelina.example.Digital.Food.Receipt.Organizer.repository.BasketRepo;
import marcelina.example.Digital.Food.Receipt.Organizer.repository.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class BasketService {

    @Autowired
    private BasketRepo basketRepository;

    @Autowired
    private BasketItemRepo basketItemRepository;

    @Autowired
    private ProductRepo productRepository;

    @Autowired
    private BasketMapper basketMapper;

    public BasketDTO getBasket(Long basketId){
        Basket basket = basketRepository.findById(basketId)
                .orElseThrow(() -> new RuntimeException("Basket not found with id: " + basketId));
        return basketMapper.mapToDto(basket);
    }

    public BasketDTO addBasketItemToBasket(Long basketId, Long productId, int itemQuantity){
        Basket basket = basketRepository.findById(basketId)
                .orElseThrow(() -> new RuntimeException("Basket not found with id: " + basketId));
        if (basket == null) {
            throw new IllegalArgumentException("Basket is null in mapToDto()");
        }
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found with id: " + productId));

        BasketItem basketItem = new BasketItem();
        basketItem.setProduct(product);
        basketItem.setQuantity(itemQuantity);
        basketItem.setBasket(basket);

        basket.getItems().add(basketItem);

        basketItemRepository.save(basketItem);
        basketRepository.save(basket);
        return basketMapper.mapToDto(basket);
    }

    public void deleteBasketItemFromBasket(Long basketId,Long basketItemId){
        Basket basket = basketRepository.findById(basketId)
                .orElseThrow(() -> new RuntimeException("Basket not found with id: " + basketId));
        BasketItem basketItem = basketItemRepository.findById(basketItemId)
                .orElseThrow(() -> new RuntimeException("BasketItem not found with id: " + basketItemId));

        if(basket.getItems().contains(basketItem)){
            basket.getItems().remove(basketItem);
            basketItem.setBasket(null);
            basketRepository.save(basket);
            basketItemRepository.delete(basketItem);
        }else{
            System.out.println("This item does not exist on basket!");
        }
    }

    public BasketDTO getContent(final Long basketId){
        return basketMapper.mapToDto(basketRepository.findById(basketId).get());
    }

    public Double getTotal(final Long basketId){
        Basket basket = basketRepository.findById(basketId)
                .orElseThrow(() -> new RuntimeException("Basket not found with id: " + basketId));
        Double total = 0.0;
        List<BasketItem> basketItemList = basket.getItems();
        for(BasketItem i:basketItemList){
            total += i.getProduct().getUnitPrice() * i.getQuantity();
        }
        return total;
    }

    public void addQuantity(Long basketId, Long basketItemId) {
        BasketItem basketItem = basketItemRepository.findById(basketItemId)
                .orElseThrow(() -> new RuntimeException("Basket not found with id: " + basketItemId));
        if(basketItem.getBasket().getId().equals(basketId)){
            basketItem.setQuantity(basketItem.getQuantity() + 1);
            basketItemRepository.save(basketItem);
        }else {
            System.out.println("This item does not exist on our basket!");
        }
    }

    public void decrementQuantity(Long basketId, Long basketItemId) {
        BasketItem basketItem = basketItemRepository.findById(basketItemId)
                .orElseThrow(() -> new RuntimeException("Basket not found with id: " + basketItemId));
        if(basketItem.getBasket().getId().equals(basketId)){
            basketItem.setQuantity(basketItem.getQuantity() - 1);
            basketItemRepository.save(basketItem);
        }else {
            System.out.println("This item does not exist on our basket!");
        }
    }
}
