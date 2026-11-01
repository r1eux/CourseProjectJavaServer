package Entities;

import jakarta.persistence.*;

@Entity
@Table(name = "reviews")
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "review_id")
    private int reviewId;

    @Column(name = "review_data", nullable = false, length = 255)
    private String reviewData;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false, foreignKey = @ForeignKey(name = "reviews_fk_products"))
    private Product product;

    @ManyToOne
    @JoinColumn(name = "buyer_id", nullable = false, foreignKey = @ForeignKey(name = "reviews_fk_buyers"))
    private Buyer buyer;

    // Конструктор без параметров


    // Геттеры и сеттеры
    public int getReviewId() {
        return reviewId;
    }

    public void setReviewId(int reviewId) {
        this.reviewId = reviewId;
    }

    public String getReviewData() {
        return reviewData;
    }

    public void setReviewData(String reviewData) {
        this.reviewData = reviewData;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Buyer getBuyer() {
        return buyer;
    }

    public void setBuyer(Buyer buyer) {
        this.buyer = buyer;
    }

    // Переопределение метода toString
    // Переопределение метода toString
    @Override
    public String toString() {
        return "Review{" +
                "reviewId=" + reviewId +
                ", reviewData='" + reviewData + '\'' +
                ", product=" + product +
                ", buyer=" + buyer +
                '}';
    }}