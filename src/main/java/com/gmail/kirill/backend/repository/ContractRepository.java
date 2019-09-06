package com.gmail.kirill.backend.repository;


import com.gmail.kirill.backend.entity.Contract;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContractRepository extends Neo4jRepository<Contract, Long> {

}
