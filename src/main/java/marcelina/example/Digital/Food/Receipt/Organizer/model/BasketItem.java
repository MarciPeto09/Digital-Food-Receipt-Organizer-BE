package marcelina.example.Digital.Food.Receipt.Organizer.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="basketItem")
public class BasketItem {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JsonBackReference(value = "product-basketItems")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "basket_id")
    @JsonBackReference(value = "basket-items")
    private Basket basket;

    private int quantity;

    public BasketItem(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }
}
