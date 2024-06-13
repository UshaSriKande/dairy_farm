package com.dfm.app.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.dfm.app.model.Product;
import com.dfm.app.model.User;


@Repository
public interface ProductRepo extends JpaRepository<Product, Long>{


}
