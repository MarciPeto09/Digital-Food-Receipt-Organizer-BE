package marcelina.example.Digital.Food.Receipt.Organizer.model.mapper;

import marcelina.example.Digital.Food.Receipt.Organizer.model.Basket;
import marcelina.example.Digital.Food.Receipt.Organizer.model.BasketItem;
import marcelina.example.Digital.Food.Receipt.Organizer.model.Product;
import marcelina.example.Digital.Food.Receipt.Organizer.model.mapper.dto.BasketDTO;
import marcelina.example.Digital.Food.Receipt.Organizer.model.mapper.dto.BasketItemDTO;
import marcelina.example.Digital.Food.Receipt.Organizer.model.mapper.dto.ProductDTO;
import marcelina.example.Digital.Food.Receipt.Organizer.repository.BasketRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BasketItemMapper {


    public BasketItemDTO mapToDto(BasketItem basketItem){
        if (basketItem == null) {
            return null;
        }
        BasketItemDTO basketItemDTO =new BasketItemDTO();
        basketItemDTO.setId(basketItem.getId());
        basketItemDTO.setQuantity(basketItem.getQuantity());
        if (basketItem.getProduct() != null) {
            ProductDTO productDTO = new ProductDTO();
            productDTO.setId(basketItem.getProduct().getId());
            productDTO.setProductName(basketItem.getProduct().getProductName());
            productDTO.setCategory(basketItem.getProduct().getCategory());
            productDTO.setUnitPrice(basketItem.getProduct().getUnitPrice());
            basketItemDTO.setProductDTO(productDTO);
        }
        if(basketItem.getBasket() != null) {
            basketItemDTO.setBasket( basketItem.getBasket());
        }
        return basketItemDTO;

    }

    public BasketItem mapToEntity(BasketItemDTO basketItemDTO){
        if( basketItemDTO == null ){
            return null;
        }
        BasketItem basketItem = new BasketItem();
        basketItem.setId(basketItemDTO.getId());
        basketItem.setQuantity(basketItemDTO.getQuantity());
        if(basketItemDTO.getBasket() != null) {
            basketItem.setBasket(basketItemDTO.getBasket());
        }

        if (basketItemDTO.getProductDTO() != null) {
            Product product = new Product();
            product.setId(basketItemDTO.getProductDTO().getId());
                product.setProductName(basketItemDTO.getProductDTO().getProductName());
            product.setCategory(basketItemDTO.getProductDTO().getCategory());
            product.setUnitPrice(basketItemDTO.getProductDTO().getUnitPrice());
            basketItem.setProduct(product);
        }
        return basketItem;

    }
}
