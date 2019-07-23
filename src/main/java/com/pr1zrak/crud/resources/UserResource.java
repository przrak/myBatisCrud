package com.pr1zrak.crud.resources;

import com.pr1zrak.crud.core.User;
import com.pr1zrak.crud.db.UserDAO;
import io.dropwizard.jersey.params.LongParam;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.Optional;

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

    @GET
    @Path("/{id}")
    public Optional<User> getUser(@PathParam("id") LongParam id) {
        Optional<User> user = dao.getUserById(id.get());
        if (user.isPresent())
            return user;

        throw new WebApplicationException("User not found");
    }

    @POST
    public void saveUser(User user) {
        dao.insertUser(user);
    }

    @PUT
    @Path("/{id}")
    public void updateUser(@PathParam("id") LongParam id, User user) {
        user.setUserId(id.get());
        dao.updateUser(user);
    }

    @DELETE
    @Path("/{id}")
    public void delete(@PathParam("id") LongParam id) {
        dao.deleteUser(id.get());
    }
}
