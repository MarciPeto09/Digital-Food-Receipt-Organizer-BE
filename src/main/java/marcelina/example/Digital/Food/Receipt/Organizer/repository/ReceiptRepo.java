package marcelina.example.Digital.Food.Receipt.Organizer.repository;


import marcelina.example.Digital.Food.Receipt.Organizer.model.Receipt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReceiptRepo extends JpaRepository<Receipt, Long> {

    @Query("SELECT r FROM Receipt r JOIN r.items i WHERE i.id = :itemId")
    List<Receipt> findAllByReceiptItemId(@Param("itemId") Long itemId);
}
