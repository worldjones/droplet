package facades;

import com.google.common.base.Strings;
import dtos.ProjectDTO;
import dtos.user.PrivateUserDto;
import entities.Project;
import entities.Role;
import entities.User;
import static facades.ProjectFacade.getProjectFacade;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.ws.rs.WebApplicationException;

import org.apache.commons.lang3.StringUtils;
import security.errorhandling.AuthenticationException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;


public class ProjectFacade {
    
    
    private static EntityManagerFactory emf;
    private static ProjectFacade instance;

    private ProjectFacade() {
    }

    /**
     * @param _emf
     * @return the instance of this facade.
     */
    public static ProjectFacade getProjectFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new ProjectFacade();
        }
        return instance;
    }    
    
    
    
     public ProjectDTO addProject(String name, String description){
        EntityManager em = emf.createEntityManager();

        Project project = new Project(name, description);

        try{

            em.getTransaction().begin();
            em.persist(project);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return new ProjectDTO(project);
    }
    
    
    
    
    public List<ProjectDTO> getProjects(){
        EntityManager em = emf.createEntityManager();
        try{
            TypedQuery<Project> q = em.createQuery("SELECT p FROM Project p", Project.class);
            return q.getResultList().stream().map(ProjectDTO::new).collect(Collectors.toList());
        } finally {
            em.close();
        }
    }
    
}
