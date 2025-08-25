package marcelina.example.Digital.Food.Receipt.Organizer.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.persistence.*;
import marcelina.example.Digital.Food.Receipt.Organizer.enumerationClasses.Role;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    @NotBlank
    @Size(min = 3, max = 20)
    private String username;

    @Email
    private String email;

    @Column(nullable = false)
    @JsonIgnore
    private String password;

    @Column(nullable = false)
    private Role role;

    private String photoUrl;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<Receipt> receipts = new ArrayList<>();

    @OneToOne
    @JsonManagedReference(value = "user-basket")
    private Basket basket;

    @OneToMany(mappedBy = "sender")
    @JsonIgnore
    private List<Message> sentMessages = new ArrayList<>();

    @OneToMany(mappedBy = "receiver")
    @JsonIgnore
    private List<Message> receivedMessages = new ArrayList<>();

    @ManyToMany(mappedBy = "participants")
    @JsonIgnore
    private List<Conversation> conversationList = new ArrayList<>();

    public User(Long id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }

}
