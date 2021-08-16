package com.example.demo2Hibernate.service;

import com.example.demo2Hibernate.entity.Clients;

import javax.persistence.*;
import javax.ws.rs.*;
import java.util.List;

@Path("/client")
public class ClientSevice {


    @GET
    @Path("/{id}")
    @Produces("application/json")
    public List<Clients> getClient(@PathParam("id") int id)
    {
        return Clients.getClients(id);
    }

    @POST
    @Consumes("application/json")
    public String setClient(Clients client) {

        client.save();

        return client.toString();
    }


}
