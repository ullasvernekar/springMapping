package com.example.mappingnew.DTO;


import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(unique = true, nullable = false)
    private String name;

    private double price;

    private double totalPrice;

    private double conversion;

    @OneToOne(mappedBy = "product")
    @JsonBackReference("ul")
    private ClubInventory clubInventory;

}