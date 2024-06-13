package com.dfm.app.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.dfm.app.model.Cow;
import com.dfm.app.model.Milk;
import com.dfm.app.model.MilkSale;
import com.dfm.app.model.Product;
import com.dfm.app.model.User;
import com.dfm.app.model.Vaccine;


@Repository
public interface VaccineRepo extends JpaRepository<Vaccine, Long>{


}
