package com.example.ecommerce.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.ecommerce.model.CartItem;
import com.example.ecommerce.model.User;

public interface CartRepository extends JpaRepository<CartItem, Long> {
	List<CartItem> findAllByUserOrderByCreatedDateDesc(User user);

}
