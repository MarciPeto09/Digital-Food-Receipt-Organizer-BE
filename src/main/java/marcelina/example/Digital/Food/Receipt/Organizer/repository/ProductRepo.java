package marcelina.example.Digital.Food.Receipt.Organizer.repository;

import marcelina.example.Digital.Food.Receipt.Organizer.enumerationClasses.ItemCategory;
import marcelina.example.Digital.Food.Receipt.Organizer.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepo extends JpaRepository<Product, Long> {


    List<Product> findByCategory(ItemCategory itemCategory);
}
