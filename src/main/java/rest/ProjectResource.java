package rest;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dtos.ProjectDTO;
import dtos.user.PrivateUserDto;
import entities.User;
import facades.ProjectFacade;
import facades.UserFacade;
import utils.EMF_Creator;

import javax.annotation.security.RolesAllowed;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
/**
 *
 * @author world
 */
@Path("project")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ProjectResource {
    
    
   private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory();

    private static final ProjectFacade FACADE = ProjectFacade.getProjectFacade(EMF);
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    @Path("all")
    public Response getAllProjects(){return Response.ok(GSON.toJson(FACADE.getProjects())).build(); }

    @GET
    public String serverIsUp() {
        return "{\"msg\":\"Person API is working\"}";
    }

    @POST
    public String addProject(String project){
        ProjectDTO p = GSON.fromJson(project, ProjectDTO.class);
        ProjectDTO pNew = FACADE.addProject(p.getName(), p.getDescription());
        return GSON.toJson(pNew);
    }

    @PUT
    @Path("{projectid}")
    public String addDeveloper(@PathParam("projectid") String project, String developer){
        User u = GSON.fromJson(developer, User.class);
        FACADE.addUser(project, project);
        return "Added User";
    }

}
