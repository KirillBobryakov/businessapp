package com.gmail.kirill.backend.entity;


import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Property;
import org.neo4j.ogm.annotation.Relationship;

import java.util.List;

@NodeEntity(label = "COMPANY")
public class Company extends Entity{

    private String name;

    @Property(name = "short_name")
    private String shortName;


    @Relationship(type = "LIKE_SELLER", direction = Relationship.OUTGOING)
    private List<Contract> contractsWhereIsSeller;

    public Company() {
    }

    public Company(String name, String shortName) {
        this.name = name;
        this.shortName = shortName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public List<Contract> getContractsWhereIsSeller() {
        return contractsWhereIsSeller;
    }

    public void setContractsWhereIsSeller(List<Contract> contractsWhereIsSeller) {
        this.contractsWhereIsSeller = contractsWhereIsSeller;
    }
}
