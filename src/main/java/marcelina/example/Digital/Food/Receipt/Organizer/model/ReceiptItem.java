package marcelina.example.Digital.Food.Receipt.Organizer.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import marcelina.example.Digital.Food.Receipt.Organizer.enumerationClasses.ItemCategory;

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

    private Double totalPrice;

    private ItemCategory category;

    @ManyToOne
    @JsonIgnore
    private Product product;

    @ManyToOne
    @JoinColumn(name = "receipt_id")
    private Receipt receipt;
}
