package com.example.graphql.springbootgraphqlexample.service.datafetcher;

import com.example.graphql.springbootgraphqlexample.model.Player;
import com.example.graphql.springbootgraphqlexample.repository.PlayerRepository;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PlayerDataFetcher implements DataFetcher<Player> {

    @Autowired
    PlayerRepository repository;

    @Override
    public Player get(DataFetchingEnvironment dataFetchingEnvironment) {

        String id = dataFetchingEnvironment.getArgument("id");

        return repository.findOne(id);
    }
}
