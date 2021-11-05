package com.example.demo2Hibernate.services;

import com.example.demo2Hibernate.entity.Operation;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import java.util.List;

@Path("/operations")
public class Operations {

    @GET
    @Produces("application/json")
    public List<Operation> getOperationList() {
        return Operation.getOperationList();
    }

}
