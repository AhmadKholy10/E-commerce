package com.example.ecommerce.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.ecommerce.model.User;
import com.example.ecommerce.model.WishList;

public interface WishListRepository extends JpaRepository<WishList, Long>{
	
	List<WishList> findAllByUserOrderByCreatedDateDesc(User user);

}
