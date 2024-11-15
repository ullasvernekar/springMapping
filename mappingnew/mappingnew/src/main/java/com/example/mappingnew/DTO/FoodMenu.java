package com.example.mappingnew.DTO;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor

public class FoodMenu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    private String description;

    private double totalPrice;

    @OneToMany(mappedBy = "foodMenu", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonManagedReference("vl")
    private List<ProductClub> productClub;

}