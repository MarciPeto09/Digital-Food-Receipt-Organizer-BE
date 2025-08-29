package marcelina.example.Digital.Food.Receipt.Organizer.model.mapper;

import marcelina.example.Digital.Food.Receipt.Organizer.model.Product;
import marcelina.example.Digital.Food.Receipt.Organizer.model.Receipt;
import marcelina.example.Digital.Food.Receipt.Organizer.model.Vendor;
import marcelina.example.Digital.Food.Receipt.Organizer.model.mapper.dto.ProductDTO;
import marcelina.example.Digital.Food.Receipt.Organizer.model.mapper.dto.ReceiptDTO;
import marcelina.example.Digital.Food.Receipt.Organizer.model.mapper.dto.VendorDTO;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

@Component
public class VendorMapper {

    public VendorDTO mapToDto(Vendor vendor){

        VendorDTO vendorDTO = new VendorDTO();

        vendorDTO.setId(vendor.getId());
        vendorDTO.setName(vendor.getName());
        vendorDTO.setLocation(vendor.getLocation());
//
//        if(vendor.getReceipts() != null){
//            List<Receipt> receipts = vendor.getReceipts();
//            for (Receipt receipt : receipts){
//                ReceiptDTO receiptDTO = new ReceiptDTO();
//                receiptDTO.setId(receipt.getId());
//                receiptDTO.setImageUrl(receipt.getImageUrl());
//                receiptDTO.setUserId(receipt.getUser().getId());
//                receiptDTO.setTotalAmount(receipt.getTotalAmount());
//                receiptDTO.setPurchaseDate(receipt.getPurchaseDate());
//                receiptDTO.setUploadDate(receipt.getUploadDate());
//                receiptDTO.setDeliveryAddress(receipt.getDeliveryAddress());
//                vendorDTO.getReceipts().add(receiptDTO);
//            }
//        }else{
//            vendorDTO.setReceipts(Collections.emptyList());
//        }


        if(vendor.getProducts() != null){
            List <Product> products = vendor.getProducts();
            for (Product product : products) {
                ProductDTO productDTO = new ProductDTO();
                productDTO.setId(product.getId());
                productDTO.setCategory(product.getCategory());
                productDTO.setQuantity(product.getQuantity());
                productDTO.setProductName(product.getProductName());
                productDTO.setUnitPrice(product.getUnitPrice());
                vendorDTO.getProductDTOs().add(productDTO);

            }
        }else{
            vendorDTO.setProductDTOs(Collections.emptyList());
        }

        return vendorDTO;
    }


    public Vendor mapToEntity(VendorDTO vendorDTO){
        Vendor vendor = new Vendor();

        vendor.setId(vendorDTO.getId());
        vendor.setName(vendorDTO.getName());
        vendor.setLocation(vendorDTO.getLocation());

        if(vendorDTO.getReceipts() != null){
            List<ReceiptDTO> receiptDTOList = vendorDTO.getReceipts();
            for (ReceiptDTO receiptDTO : receiptDTOList){
                Receipt receipt = new Receipt();
                receipt.setId(receiptDTO.getId());
                receipt.setImageUrl(receiptDTO.getImageUrl());
                receipt.setPurchaseDate(receiptDTO.getPurchaseDate());
                receipt.setTotalAmount(receiptDTO.getTotalAmount());
                receipt.setDeliveryAddress(receiptDTO.getDeliveryAddress());
                vendor.getReceipts().add(receipt);
            }
        }else{
            vendor.setReceipts(Collections.emptyList());
        }

        if(vendorDTO.getProductDTOs() != null){
            List<ProductDTO> productDTOS = vendorDTO.getProductDTOs();
            for(ProductDTO productDTO : productDTOS){
                Product product = new Product();
                product.setProductName(productDTO.getProductName());
                product.setCategory(productDTO.getCategory());
                product.setId(productDTO.getId());
                product.setQuantity(productDTO.getQuantity());
                product.setUnitPrice(productDTO.getUnitPrice());
                vendor.getProducts().add(product);
            }
        }else{
            vendor.setProducts(Collections.emptyList());
        }

        return vendor;
    }
}
