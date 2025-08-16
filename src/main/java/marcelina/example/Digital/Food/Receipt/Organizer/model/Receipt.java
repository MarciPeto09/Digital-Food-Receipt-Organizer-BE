package marcelina.example.Digital.Food.Receipt.Organizer.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="receipt")
public class    Receipt {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime uploadDate;

    private LocalDateTime purchaseDate;

    private Double totalAmount;

    @ManyToOne
    @JoinColumn(name = "vendor_id")
    private Vendor vendor;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "receipt", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ReceiptItem> items = new ArrayList<>();

    private String imageUrl;

    public Receipt(Long id, LocalDateTime uploadDate, Double totalAmount, User user, List<ReceiptItem> items) {
        this.id = id;
        this.uploadDate = uploadDate;
        this.totalAmount = totalAmount;
        this.user = user;
        this.items = items;
    }
}
