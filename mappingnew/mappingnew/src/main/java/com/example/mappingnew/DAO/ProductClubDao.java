package com.example.mappingnew.DAO;


import com.example.mappingnew.DTO.FoodMenu;
import com.example.mappingnew.DTO.ProductClub;
import com.example.mappingnew.REPOSITORY.ProductClubRepository;
import com.example.mappingnew.SERVICES.ProductClubServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ProductClubDao {
    @Autowired
    public ProductClubRepository productClubRepository;

    public ProductClub save(ProductClub productClub){
        return productClubRepository.save(productClub);
    }


}
