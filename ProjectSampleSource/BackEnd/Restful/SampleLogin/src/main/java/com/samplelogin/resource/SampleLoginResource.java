package com.samplelogin.resource;

import com.samplelogin.model.User;
import com.samplelogin.service.SampleLoginService;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("login")
@Consumes(MediaType.APPLICATION_JSON)
public class SampleLoginResource {

    SampleLoginService sampleLoginService;

    @POST
    @Produces(MediaType.TEXT_PLAIN)
    public String sampleLogin(User user){
        sampleLoginService = new SampleLoginService();
        return sampleLoginService.userAuth(user);
    }

}
