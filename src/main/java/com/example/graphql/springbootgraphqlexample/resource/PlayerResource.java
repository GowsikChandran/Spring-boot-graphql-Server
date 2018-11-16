package com.example.graphql.springbootgraphqlexample.resource;


import com.example.graphql.springbootgraphqlexample.service.GraphQLService;
import graphql.ExecutionResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/rest/footballPlayers")
@RestController
public class PlayerResource {


    @Autowired
    GraphQLService graphQLService;

    @PostMapping
    public ResponseEntity<Object> getAllPlayers(@RequestBody String query) {

        ExecutionResult executionResult = graphQLService.getGraphQL().execute(query);

        return new ResponseEntity<>(executionResult, HttpStatus.OK);
    }


}
