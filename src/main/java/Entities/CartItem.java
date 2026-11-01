package Entities;
import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "itemcart")
public class CartItem implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "itemcart_id")
    private int cartItemId;

    @ManyToOne(cascade = {CascadeType.PERSIST,CascadeType.MERGE})
    @JoinColumn(name = "cart_id", nullable = false, foreignKey = @ForeignKey(name = "cart_items_fk_cart"))
    private Cart cart;

    @ManyToOne(cascade = {CascadeType.PERSIST,CascadeType.MERGE})
    @JoinColumn(name = "product_id", nullable = false, foreignKey = @ForeignKey(name = "cart_items_fk_products"))
    private Product product;

    @Column(name = "amount", nullable = false)
    private int amount;

    // Getters and Setters
    public int getCartItemId() {
        return cartItemId;
    }

    public void setCartItemId(int cartItemId) {
        this.cartItemId = cartItemId;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
