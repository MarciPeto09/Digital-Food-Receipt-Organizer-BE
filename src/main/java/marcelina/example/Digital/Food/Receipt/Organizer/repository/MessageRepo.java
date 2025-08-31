package marcelina.example.Digital.Food.Receipt.Organizer.repository;

import marcelina.example.Digital.Food.Receipt.Organizer.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface MessageRepo extends JpaRepository<Message,Long> {
    @Query("SELECT m FROM Message m " +
            "WHERE (m.sender.id = :user1 AND m.receiver.id = :user2) " +
            "   OR (m.sender.id = :user2 AND m.receiver.id = :user1) " +
            "ORDER BY m.dateTime ASC")
    List<Message> findConversationBetweenUsers(@Param("user1") Long user1,
                                               @Param("user2") Long user2);


    @Query("SELECT m FROM Message m WHERE m.sender.id = :senderId AND m.receiver.id = :receiverId AND m.seen = false")
    List<Message> findBySenderIdAndRecipientIdAndSeenFalse(@Param("senderId") Long senderId,
                                                           @Param("receiverId") Long receiverId);

    @Query("SELECT COUNT(m) FROM Message m " +
            "WHERE m.sender.id = :senderId AND m.receiver.id = :receiverId AND m.seen = false ")
    int countUnseenMessagesPerSender(@Param("senderId") Long senderId,
                                     @Param("receiverId") Long receiverId);

}
