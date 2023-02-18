package com.rafaeldeluca.Ecommerce.Java.repositories;

import com.rafaeldeluca.Ecommerce.Java.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
