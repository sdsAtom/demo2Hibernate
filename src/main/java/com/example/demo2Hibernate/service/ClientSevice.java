package com.example.demo2Hibernate.service;

import com.example.demo2Hibernate.entity.Clients;

import javax.ws.rs.*;

@Path("/client")
public class ClientSevice {

    @GET
    @Path("/{id}")
    @Produces("application/json")
    public Clients getClient(@PathParam("id") int id)
    {
        Clients client = new Clients();
        client.setName("MyDesignStudio");
        client.setId(id);

        return client;
    }

    @POST
    @Consumes("application/json")
    public String setClient(Clients client) {

        return client.toString();
    }


}
