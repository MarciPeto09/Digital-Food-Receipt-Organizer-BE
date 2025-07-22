package marcelina.example.Digital.Food.Receipt.Organizer.model.mapper;

import marcelina.example.Digital.Food.Receipt.Organizer.model.Receipt;
import marcelina.example.Digital.Food.Receipt.Organizer.model.Vendor;
import marcelina.example.Digital.Food.Receipt.Organizer.model.mapper.dto.ReceiptDTO;
import marcelina.example.Digital.Food.Receipt.Organizer.model.mapper.dto.VendorDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

@Component
public class VendorMapper {

    @Autowired
    private ReceiptMapper receiptMapper;

    public VendorDTO mapToDto(Vendor vendor){

        VendorDTO vendorDTO = new VendorDTO();

        vendorDTO.setId(vendor.getId());
        vendorDTO.setName(vendor.getName());
        vendorDTO.setLocation(vendor.getLocation());

        if(vendor.getReceipts() != null){
            List<ReceiptDTO> receipts = vendor.getReceipts().stream()
                    .map(receiptMapper::mapToDto)
                    .toList();
            vendorDTO.setReceipts(receipts);
        }else{
            vendorDTO.setReceipts(Collections.emptyList());
        }

        return vendorDTO;
    }


    public Vendor mapToEntity(VendorDTO vendorDTO){
        Vendor vendor = new Vendor();

        vendor.setId(vendorDTO.getId());
        vendor.setName(vendorDTO.getName());
        vendor.setLocation(vendorDTO.getLocation());

        if(vendorDTO.getReceipts() != null){
            List<Receipt> receipts = vendorDTO.getReceipts().stream()
                    .map(receiptMapper::mapToEntity)
                    .toList();
            vendor.setReceipts(receipts);
        }else{
            vendor.setReceipts(Collections.emptyList());
        }

        return vendor;
    }
}
