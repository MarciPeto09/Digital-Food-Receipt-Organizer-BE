package marcelina.example.Digital.Food.Receipt.Organizer.service;

import marcelina.example.Digital.Food.Receipt.Organizer.model.Vendor;
import marcelina.example.Digital.Food.Receipt.Organizer.model.mapper.VendorMapper;
import marcelina.example.Digital.Food.Receipt.Organizer.model.mapper.dto.VendorDTO;
import marcelina.example.Digital.Food.Receipt.Organizer.repository.ReceiptItemRepo;
import marcelina.example.Digital.Food.Receipt.Organizer.repository.VendorRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VendorService {

    @Autowired
    private VendorRepo vendorRepository;

    @Autowired
    private VendorMapper vendorMapper;

    public void createVendor(VendorDTO request){
        vendorRepository.save(vendorMapper.mapToEntity(request));
    }

    public VendorDTO getVendorById(Long vendorId){
        return vendorMapper.mapToDto(vendorRepository.findById(vendorId).get());
    }

    public List<VendorDTO> getAllVendors(){
        return vendorRepository.findAll().stream()
                .map(vendorMapper::mapToDto)
                .toList();    }

    public VendorDTO updateVendor(Long vendorId, VendorDTO request){
        VendorDTO vendorDTO = vendorMapper.mapToDto(vendorRepository.findById(vendorId).get());
        vendorDTO.setId(request.getId());
        vendorDTO.setName(request.getName());
        vendorDTO.setReceipts(request.getReceipts());
        return vendorDTO;
    }

    public void deleteVendor(Long vendorId){
        vendorRepository.deleteById(vendorId);
    }
}
