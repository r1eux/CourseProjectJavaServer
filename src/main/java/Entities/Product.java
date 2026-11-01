package Entities;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "products")
public class Product implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private int productId;

    @Column(name = "prodname", nullable = false)
    private String prodName;

    @Column(name = "price", nullable = false)
    private double price;
    @Column(name="amount")
    private int amount;
    @Column(name = "image")
    private String image;
    @Column(name = "warranty")
    private String warranty;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "color_id", foreignKey = @ForeignKey(name = "products_fk_colors"))
    private Color color;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "category_id", foreignKey = @ForeignKey(name = "products_fk_categories"))
    private Category category;

    @ManyToOne(cascade =CascadeType.ALL)
    @JoinColumn(name = "producer_id", foreignKey = @ForeignKey(name = "products_fk_producers"))
    private Producer producer;

    // Getters and Setters
    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getProdName() {
        return prodName;
    }

    public void setProdName(String prodName) {
        this.prodName = prodName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String picUrl) {
        this.image = picUrl;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Producer getProducer() {
        return producer;
    }

    public void setProducer(Producer producer) {
        this.producer = producer;
    }
    public int getAmount() {
        return amount;
    }
    public void setAmount(int amount) {
        this.amount = amount;
    }
    public String getWarranty() {
        return warranty;
    }
    public void setWarranty(String warranty) {
        this.warranty = warranty;
    }
    public Category getCategory() {
        return category;
    }
    public void setCategory(Category category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "Product{" +
                "productId=" + productId +
                ", prodName='" + prodName + '\'' +
                ", price=" + price +
                ", picUrl='" + image + '\'' +
                ", color=" + (color != null ? color.toString() : "null") + // Выводим строковое представление цвета, если оно не null
                ", amount=" + amount +
                ", producer=" + (producer != null ? producer.toString() : "null") + // Выводим строковое представление производителя, если оно не null
                '}';
    }

}