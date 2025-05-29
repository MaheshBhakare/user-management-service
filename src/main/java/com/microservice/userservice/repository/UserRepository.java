package com.microservice.userservice.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.microservice.userservice.entity.Users;

@Repository
public interface UserRepository extends JpaRepository<Users, Integer> {

	Optional<Users> findByEmail(String email);

}
