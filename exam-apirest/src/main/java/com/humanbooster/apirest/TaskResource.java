package com.humanbooster.apirest;

import java.net.URI;
import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.humanbooster.dao.TaskDao;
import com.humanbooster.model.Task;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriBuilder;

@Path("/tasks")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class TaskResource {
    private final SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
    private final TaskDao taskDao = new TaskDao(sessionFactory);


    @GET
    public List<Task> getAll(){
        return taskDao.tout();
    }

    @GET
    @Path("/{id}")
    public Task getById(@PathParam("id") int id){
        return taskDao.lire(id);
    }

    @POST
    public Response create(Task task){
        taskDao.creer(task);
        URI uri = UriBuilder.fromPath("/tasks/" + task.getId()).build();
        return Response.created(uri).build();
    }

    @PUT
    @Path("/{id}")
    public Response update(@PathParam("id") int id, Task task){
        task.setId(id);
        taskDao.mettreAJour(task);
        URI uri = UriBuilder.fromPath("/tasks/" + task.getId()).build();
        return Response.ok(uri).build();
    }

    @DELETE
    @Path("/{id}")
    public void delete(@PathParam("id") int id){
        taskDao.supprimer(id);
    }
}
