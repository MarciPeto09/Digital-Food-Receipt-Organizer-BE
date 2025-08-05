package marcelina.example.Digital.Food.Receipt.Organizer.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="receiptItem")
public class ReceiptItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String itemName;

    private int quantity;

    private Double unitPrice;

    private ItemCategory category;

    @ManyToMany(mappedBy = "items")
    private List<Receipt> receipt;
}
