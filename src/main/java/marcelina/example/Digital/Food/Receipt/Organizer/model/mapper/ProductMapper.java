package marcelina.example.Digital.Food.Receipt.Organizer.model.mapper;

import marcelina.example.Digital.Food.Receipt.Organizer.model.BasketItem;
import marcelina.example.Digital.Food.Receipt.Organizer.model.Product;
import marcelina.example.Digital.Food.Receipt.Organizer.model.ReceiptItem;
import marcelina.example.Digital.Food.Receipt.Organizer.model.mapper.dto.BasketItemDTO;
import marcelina.example.Digital.Food.Receipt.Organizer.model.mapper.dto.ProductDTO;
import marcelina.example.Digital.Food.Receipt.Organizer.model.mapper.dto.ReceiptItemDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import java.util.Collections;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Component
public class ProductMapper {

    @Autowired
    @Lazy
    private ReceiptItemMapper receiptItemMapper;


    public ProductDTO maptoDto(Product product){
        if (product == null) {
            return null;
        }
        ProductDTO productDTO = new ProductDTO();
        productDTO.setId(product.getId());
        productDTO.setCategory(product.getCategory());
        productDTO.setQuantity(product.getQuantity());
        productDTO.setProductName(product.getProductName());
        productDTO.setUnitPrice(product.getUnitPrice());

        if(product.getReceiptItems() != null){
            List<ReceiptItemDTO> listOfReceiptItems = product.getReceiptItems().stream()
                    .map(i -> receiptItemMapper.mapToDto(i))
                    .toList();
            productDTO.setReceiptItemsDto(listOfReceiptItems);
        }else{
            productDTO.setReceiptItemsDto(Collections.emptyList());
        }

        if(product.getBasketItems() != null){
            List<BasketItemDTO> listOfBasketItems = product.getBasketItems().stream()
                    .map(bi -> {
                        BasketItemDTO basketItemDTO = new BasketItemDTO();
                        basketItemDTO.setId(bi.getId());
                        basketItemDTO.setQuantity(bi.getQuantity());
                        return basketItemDTO;})
                    .toList();
            productDTO.setBasketItemsDto(listOfBasketItems);
        }else{
            productDTO.setBasketItemsDto(Collections.emptyList());
        }


        return productDTO;
    }

    public Product mapToEntity(ProductDTO productDTO){
        if (productDTO == null) {
            return null;
        }
        Product product = new Product();
        product.setProductName(productDTO.getProductName());
        product.setCategory(productDTO.getCategory());
        product.setId(productDTO.getId());
        product.setQuantity(productDTO.getQuantity());
        product.setUnitPrice(productDTO.getUnitPrice());

        if(productDTO.getReceiptItemsDto() != null){
            List<ReceiptItem> listOfReceiptItems = productDTO.getReceiptItemsDto().stream()
                    .map(i -> receiptItemMapper.mapToEntity(i))
                    .toList();
            product.setReceiptItems(listOfReceiptItems);
        }else{
            product.setReceiptItems(Collections.emptyList());
        }

        if(productDTO.getBasketItemsDto() != null){
            List<BasketItem> listOfBasketItems = productDTO.getBasketItemsDto().stream()
                    .map(bi -> {
                        BasketItem basketItem = new BasketItem();
                        basketItem.setId(bi.getId());
                        basketItem.setQuantity(bi.getQuantity());
                        return basketItem;})
                    .toList();
            product.setBasketItems(listOfBasketItems);
        }else{
            product.setBasketItems(Collections.emptyList());
        }
        return product;
    }
}
