package com.springboot.dao;

import com.springboot.entities.Player;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Player, Integer> {
}

