package com.dfm.app.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.dfm.app.model.User;


@Repository
public interface UserRepo extends JpaRepository<User, Long>{

	@Query( value = "select * from users where email = :email", nativeQuery = true)
	User findbyEmail(@Param("email") String email);


	@Query( value = "select * from users where usertype = 'user'", nativeQuery = true)
	List<User> getAllUsers();

	@Query( value = "select * from users where usertype = 'houseowner'", nativeQuery = true)
	List<User> findAllOwners();

	@Query( value = "select * from users where id = :id", nativeQuery = true)
	User getUserById(@Param("id") Long id);




//	@Query( value = "delete from users where email = :userMail", nativeQuery = true)
//	void deleteByUserMail(@Param("userMail") String userMail);
}
