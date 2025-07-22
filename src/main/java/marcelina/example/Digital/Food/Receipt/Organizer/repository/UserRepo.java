package marcelina.example.Digital.Food.Receipt.Organizer.repository;


import marcelina.example.Digital.Food.Receipt.Organizer.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<User,Long> {
}
