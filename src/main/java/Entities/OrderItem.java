package Entities;
import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "itemorder")
public class OrderItem implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "itemorder_id")
    private int orderItemId;

    @ManyToOne(cascade = {CascadeType.PERSIST,CascadeType.MERGE})
    @JoinColumn(name = "order_id", nullable = false, foreignKey = @ForeignKey(name = "order_items_fk_orders"))
    private Order order;

    @ManyToOne(cascade = {CascadeType.PERSIST,CascadeType.MERGE})
    @JoinColumn(name = "product_id", nullable = false, foreignKey = @ForeignKey(name = "order_items_fk_products"))
    private Product product;

    @Column(name = "amount", nullable = false)
    private int amount;

    @Column(name = "sum_price", nullable = false)
    private double pricePurchase;

    // Getters and Setters
    public int getOrderItemId() {
        return orderItemId;
    }

    public void setOrderItemId(int orderItemId) {
        this.orderItemId = orderItemId;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
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

    public double getPricePurchase() {
        return pricePurchase;
    }

    public void setPricePurchase(double pricePurchase) {
        this.pricePurchase = pricePurchase;
    }
    @Override
    public String toString() {
        return "OrderItem{" +
                "orderItemId=" + orderItemId +
                ", order=" + (order != null ? order.toString() : "null") + // Убедитесь, что 'order' не равен null
                ", product=" + (product != null ? product.toString() : "null") + // Убедитесь, что 'product' не равен null
                ", amount=" + amount +
                ", pricePurchase=" + pricePurchase +
                '}';
    }
}
