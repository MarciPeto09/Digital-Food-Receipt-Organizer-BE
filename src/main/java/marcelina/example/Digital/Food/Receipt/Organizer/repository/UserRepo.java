package marcelina.example.Digital.Food.Receipt.Organizer.repository;


import marcelina.example.Digital.Food.Receipt.Organizer.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<User,Long> {
    Optional<User> findByUsername(String username);
}
