package Entities;


import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "sexes")
public class Sex implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sex_id")
    private int sexId;

    @Column(name = "sex_value", nullable = false)
    private String sexValue;

    // Getters and Setters
    public int getSexId() {
        return sexId;
    }

    public void setSexId(int sexId) {
        this.sexId = sexId;
    }

    public String getSexValue() {
        return sexValue;
    }

    public void setSexValue(String sexValue) {
        this.sexValue = sexValue;
    }
}