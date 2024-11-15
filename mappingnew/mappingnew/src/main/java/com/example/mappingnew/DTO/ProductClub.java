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
public class ProductClub {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private double quantity;
    private double totalPrice;

    @ManyToOne(fetch = FetchType.EAGER)
    @JsonBackReference("vl")
    @JoinColumn
    private FoodMenu foodMenu;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn
    private Product product;
}