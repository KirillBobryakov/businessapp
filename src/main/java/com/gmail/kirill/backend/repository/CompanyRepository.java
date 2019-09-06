package com.gmail.kirill.backend.repository;


import com.gmail.kirill.backend.entity.Company;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CompanyRepository extends Neo4jRepository<Company, Long> {

    List<Company> findDistinctByNameContainingOrShortNameContainingAllIgnoreCase(String name, String shortName);
}
