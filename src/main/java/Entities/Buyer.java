package Entities;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "buyers")
public class Buyer implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "buyer_id")
    private int buyerId;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "phone_number", nullable = false)
    private String phoneNumber;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id", foreignKey = @ForeignKey(name = "persons_fk_addresses"))
    private Address address;

    @ManyToOne
    @JoinColumn(name = "sex_id", foreignKey = @ForeignKey(name = "persons_fk_sexes"))
    private Sex sex;

    @Column(name = "email", nullable = false)
    private String email;

    // Getters and Setters
    public int getBuyerId() {
        return buyerId;
    }

    public void setBuyerId(int personId) {
        this.buyerId = personId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Sex getSex() {
        return sex;
    }

    public void setSex(Sex sex) {
        this.sex = sex;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
}
