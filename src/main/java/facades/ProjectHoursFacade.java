package facades;

import com.google.common.base.Strings;
import dtos.ProjectDTO;
import dtos.ProjectHoursDTO;
import dtos.user.PrivateUserDto;
import entities.Project;
import entities.ProjectHours;
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


public class ProjectHoursFacade {
    
    
    private static EntityManagerFactory emf;
    private static ProjectHoursFacade instance;

    private ProjectHoursFacade() {
    }

    /**
     * @param _emf
     * @return the instance of this facade.
     */
    public static ProjectHoursFacade getProjectHoursFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new ProjectHoursFacade();
        }
        return instance;
    }    
    
    
    
    
    public List<ProjectHoursDTO> getProjectHours(){
        EntityManager em = emf.createEntityManager();
        try{
            TypedQuery<ProjectHours> q = em.createQuery("SELECT p FROM ProjectHours p", ProjectHours.class);
            return q.getResultList().stream().map(ProjectHoursDTO::new).collect(Collectors.toList());
        } finally {
            em.close();
        }
    }
    
}

