package com.example.graphql.springbootgraphqlexample.service.datafetcher;


import com.example.graphql.springbootgraphqlexample.model.Player;
import com.example.graphql.springbootgraphqlexample.repository.PlayerRepository;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AllPlayersDataFetcher implements DataFetcher<List<Player>> {

    @Autowired
    PlayerRepository repository;


    @Override
    public List<Player> get(DataFetchingEnvironment dataFetchingEnvironment) {
        return repository.findAll();
    }
}
