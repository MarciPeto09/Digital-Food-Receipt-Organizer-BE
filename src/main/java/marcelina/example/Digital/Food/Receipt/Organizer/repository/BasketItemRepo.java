package marcelina.example.Digital.Food.Receipt.Organizer.repository;

import marcelina.example.Digital.Food.Receipt.Organizer.model.BasketItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BasketItemRepo extends JpaRepository<BasketItem, Long> {
}
