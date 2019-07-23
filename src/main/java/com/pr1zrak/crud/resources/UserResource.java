package com.pr1zrak.crud.resources;

import com.pr1zrak.crud.core.User;
import com.pr1zrak.crud.db.UserDAO;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/users")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UserResource {
    private final UserDAO dao;

    public UserResource(UserDAO dao) {
        this.dao = dao;
    }

    @GET
    public List<User> getUsers() {
        return dao.getAllUsers();
    }
}
