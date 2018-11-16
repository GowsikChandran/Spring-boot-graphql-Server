package com.example.graphql.springbootgraphqlexample.repository;

import com.example.graphql.springbootgraphqlexample.model.Player;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlayerRepository extends JpaRepository<Player, String> {
}
