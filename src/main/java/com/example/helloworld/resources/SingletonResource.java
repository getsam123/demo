package com.example.helloworld.resources;

import com.example.helloworld.core.SingletonDemo;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/single")
@Produces(MediaType.TEXT_PLAIN)
public class SingletonResource {

    @Inject
    private SingletonDemo demo;

    @GET
    public String getSingletonData(){
        return demo.getSingletonDemo();
    }
}
