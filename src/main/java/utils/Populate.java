package utils;


import com.google.common.base.Strings;
import facades.ProjectFacade;
import facades.UserFacade;

import javax.persistence.EntityManagerFactory;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Populate {
    private final EntityManagerFactory emf;


    public static void main(String[] args) {
        new Populate(EMF_Creator.createEntityManagerFactory()).populateAll();
    }

    public Populate(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public List<String> populateAll() {
        List<String> populated = new ArrayList<>();
        if(populateUsers())
            populated.add("users");
        if(populateProjects())
            populated.add("projects");

        return populated;
    }

    /**
     *
     * @return Boolean regarding table being populated or not.
     *
     * */
    public boolean populateUsers() throws IllegalArgumentException {
        UserFacade userFacade = UserFacade.getInstance(this.emf);

        if (!userFacade.getUsers().isEmpty()) return false;

        // NOTICE: Always set your password as environment variables.
        String password_admin = "test";
        String password_user = "test";

        boolean isDeployed = System.getenv("DEPLOYED") != null;
        if(isDeployed) {
            password_user = System.getenv("PASSWORD_DEFAULT_USER");
            password_admin = System.getenv("PASSWORD_DEFAULT_ADMIN");

            // Do not allow "empty" passwords in production.
            if(Strings.isNullOrEmpty(password_admin) || password_admin.trim().length() < 3 || Strings.isNullOrEmpty(password_user) || password_user.trim().length() < 3)
                throw new IllegalArgumentException("FAILED POPULATE OF USERS: Passwords were empty or less than 3 characters? Are environment variables: [PASSWORD_DEFAULT_USER, PASSWORD_DEFAULT_ADMIN] set?");
        }

        userFacade._create("user", password_user, new ArrayList<>());
        userFacade._create("admin", password_admin, Collections.singletonList("admin"));

        userFacade.addUser("worldjones", "cph51738", "worldjones@gmail.com", 87654321, 500);
        userFacade.addUser("worldjones", "password", "test@gmail.com", 12345678, 800);
        
        
        return true;
        
    }
        
        
     public boolean populateProjects() throws IllegalArgumentException{
        ProjectFacade projectFacade = ProjectFacade.getProjectFacade(this.emf);

        if(!projectFacade.getProjects().isEmpty()) return false;

        projectFacade.addProject("First project", "Number 1");
        projectFacade.addProject("Seconds project", "Number 2");

        return true;
    }
        
    

}
