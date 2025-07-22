package marcelina.example.Digital.Food.Receipt.Organizer.repository;

import marcelina.example.Digital.Food.Receipt.Organizer.model.Receipt;
import marcelina.example.Digital.Food.Receipt.Organizer.model.ReceiptItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReceiptItemRepo extends JpaRepository<ReceiptItem, Long> {
}
