package com.example.restapi;

import javax.ws.rs.*;
import javax.ws.rs.core.*;

@Path("/example")
public class RestCrud {

    @GET
    @Path("/hello")
    @Produces(MediaType.TEXT_PLAIN)
    public String sayHello() {
        return "Hello, Jersey!";
    }

    @POST
    @Path("/greet")
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.TEXT_PLAIN)
    public String greetUser(String name) {
        return "Hello, " + name + "!";
    }
}
