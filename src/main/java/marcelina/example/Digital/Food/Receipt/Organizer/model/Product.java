package marcelina.example.Digital.Food.Receipt.Organizer.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import marcelina.example.Digital.Food.Receipt.Organizer.enumerationClasses.ItemCategory;

import java.util.List;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String productName;

    private int quantity;

    private Double unitPrice;

    @Enumerated(EnumType.STRING)
    private ItemCategory category;

    @ManyToOne
    @JoinColumn(name = "vendor_id")
    private Vendor vendor;

    @OneToMany(mappedBy = "product")
    @JsonIgnore
    private List<ReceiptItem> receiptItems;

    @OneToMany(mappedBy = "product")
    @JsonManagedReference(value = "product-basketItems")
    private List<BasketItem> basketItems;
}
