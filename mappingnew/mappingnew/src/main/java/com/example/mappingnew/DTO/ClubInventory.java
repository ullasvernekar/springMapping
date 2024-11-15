package com.example.mappingnew.DTO;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class ClubInventory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private double quantity;

    private String description;

    private double balanceType;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn
    @JsonManagedReference("ul")
    private Product product;
}