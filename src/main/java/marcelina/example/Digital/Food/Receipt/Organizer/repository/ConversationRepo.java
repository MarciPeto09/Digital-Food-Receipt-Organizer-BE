package marcelina.example.Digital.Food.Receipt.Organizer.repository;

import marcelina.example.Digital.Food.Receipt.Organizer.model.Conversation;
import marcelina.example.Digital.Food.Receipt.Organizer.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface ConversationRepo extends JpaRepository<Conversation, Long> {

    @Query("SELECT c FROM Conversation c JOIN c.participants p WHERE p.id = :userId")
    List<Conversation> getConversationsForUser(@Param("userId") Long userId);

    @Query(value = """
        SELECT c.* FROM conversation c
        JOIN conversation_participants cp1 ON c.id = cp1.conversation_id
        JOIN conversation_participants cp2 ON c.id = cp2.conversation_id
        WHERE cp1.user_id = :user1Id AND cp2.user_id = :user2Id
        GROUP BY c.id
        HAVING COUNT(*) = 2
        """, nativeQuery = true)
    Optional<Conversation> findConversationBetweenUsers(@Param("user1Id") Long user1Id,
                                                        @Param("user2Id") Long user2Id);
}
