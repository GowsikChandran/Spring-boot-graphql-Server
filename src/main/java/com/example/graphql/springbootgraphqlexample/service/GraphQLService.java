package com.example.graphql.springbootgraphqlexample.service;


import com.example.graphql.springbootgraphqlexample.model.Player;
import com.example.graphql.springbootgraphqlexample.repository.PlayerRepository;
import com.example.graphql.springbootgraphqlexample.service.datafetcher.AllPlayersDataFetcher;
import com.example.graphql.springbootgraphqlexample.service.datafetcher.PlayerDataFetcher;
import graphql.GraphQL;
import graphql.schema.GraphQLSchema;
import graphql.schema.idl.RuntimeWiring;
import graphql.schema.idl.SchemaGenerator;
import graphql.schema.idl.SchemaParser;
import graphql.schema.idl.TypeDefinitionRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
import java.util.stream.Stream;

@Service
public class GraphQLService {

    @Autowired
    PlayerRepository playerRepository;

    @Value("classpath:players.graphql")
    private Resource resource;

    private GraphQL graphQL;

    @Autowired
    private AllPlayersDataFetcher allPlayersDataFetcher;
    @Autowired
    private PlayerDataFetcher playerDataFetcher;

    //Load schema at the application start up
    @PostConstruct
    private void loadSchema() throws IOException {

        //load Players into the player repository
        loadDataIntoHSQL();
        //get the schema
        File schemaFile = resource.getFile();

        //parse schema
        TypeDefinitionRegistry typeDefinitionRegistry = new SchemaParser().parse(schemaFile);
        RuntimeWiring wiring = buildRuntimeWiring();
        GraphQLSchema schema = new SchemaGenerator().makeExecutableSchema(typeDefinitionRegistry, wiring);
        graphQL = GraphQL.newGraphQL(schema).build();


    }

    private void loadDataIntoHSQL() {

        Stream.of(
                new Player("1", "Wayne Rooney", "England",
                        new String[]{
                                "Everton,Manchester United,D.C. United"
                        }, "24 October 1985", "33"),
                new Player("2", "Cristiano Ronaldo", "Portugal",
                        new String[]{
                                "Sporting CP,Manchester United,Real Madrid,Juventus"
                        }, "5 February 1985", "33"),
                new Player("3", "Lionel Messi ", "Argentina",
                        new String[]{
                                "Barcelona"
                        }, "24 June 1987", "31"),
                new Player("4", "Sergio Ramos", "Spain",
                        new String[]{
                                "Sevilla, Real Madrid"
                        }, "30 March 1986", "32"),
                new Player("5", "Thierry Henry", "France",
                        new String[]{
                                "Monaco, Juventus, Arsenal, Barcelona, New York Red Bulls"
                        }, "17 August 1977", "41"),
                new Player("6", "Zinedine Zidane", "France",
                        new String[]{
                                "Cannes,Bordeaux,Juventus,Real Madrid"
                        }, "23 June 1972", "46"),
                new Player("7", "Zlatan Ibrahimović", "Sweden",
                        new String[]{
                                "Malmö FF,Ajax,Juventus,Inter Milan,Barcelona,Milan,PSG,Manchester United,LA Galaxy"
                        }, "3 October 1981", "37"),
                new Player("8", "Andrea Pirlo", "Italy",
                        new String[]{
                                "Brescia,Internazionale,Milan,Juventus,New York City"
                        }, "19 May 1979", "39"),
                new Player("9", "Gianluigi Buffon", "Italy",
                        new String[]{
                                "Parma ,Juventus, PSG"
                        }, "28 January 1978", "40"),
                new Player("10", "Bastian Schweinsteiger", "Germany",
                        new String[]{
                                "Bayern Munich ,Manchester United, Chicago Fire"
                        }, "1 August 1984", "34")

        ).forEach(player -> {
            playerRepository.save(player);
        });
    }

    private RuntimeWiring buildRuntimeWiring() {


        return RuntimeWiring.newRuntimeWiring()
                .type("Query", typeWiring -> typeWiring
                        .dataFetcher("allPlayers", allPlayersDataFetcher)
                        .dataFetcher("player", playerDataFetcher)
                ).build();
    }

    public GraphQL getGraphQL() {
        return graphQL;
    }
}
