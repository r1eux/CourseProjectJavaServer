package Entities;


import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "producers")
public class Producer implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "producer_id")
    private int producerId;

    @Column(name = "producer_name", nullable = false)
    private String producer;
    @Column(name = "producer_country", nullable = false)
    private String producer_country;
    // Getters and Setters
    public int getProducerId() {
        return producerId;
    }

    public void setProducerId(int producerId) {
        this.producerId = producerId;
    }

    public String getProducer() {
        return producer;
    }

    public void setProducer(String producer) {
        this.producer = producer;
    }
    public String getProducer_country() {
        return producer_country;
    }
    public void setProducer_country(String producer_country) {
        this.producer_country = producer_country;
    }
}