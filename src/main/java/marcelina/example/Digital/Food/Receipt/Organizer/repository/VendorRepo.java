package marcelina.example.Digital.Food.Receipt.Organizer.repository;

import marcelina.example.Digital.Food.Receipt.Organizer.model.Vendor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VendorRepo extends JpaRepository<Vendor,Long> {
}
