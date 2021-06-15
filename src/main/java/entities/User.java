package entities;

import java.io.Serializable;
import java.util.*;
import java.util.stream.Collectors;
import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.mindrot.jbcrypt.BCrypt;

@Data
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(unique = true)
    private String username;
    private String displayName;
    private String email;
    private int phone;
    private double billingPrHour;

    private String password;

    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(name = "user_roles",
            joinColumns = {@JoinColumn(name = "fk_user_id")},
            inverseJoinColumns = {@JoinColumn(name = "fk_role")})
    
    private Set<Role> roles = new HashSet<>();
    
    private Date createdAt;
    private Date updatedAt;
    
    
    
    @ManyToMany(cascade = CascadeType.PERSIST)
    private List<Project> projects;
    
    @OneToMany(cascade = CascadeType.PERSIST)
    private List<ProjectHours> projectHours;

    public User(String username, String password, String email, int phone, double billingPrHour) {
        this.username = username;
        this.password = generateHashedPassword(password);
        this.email = email;
        this.phone = phone;
        this.billingPrHour = billingPrHour;
        this.projects = new ArrayList<>();
        this.projectHours = new ArrayList<>();
    }

    private String generateHashedPassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

    public void setPassword(String password) {
        this.password = generateHashedPassword(password);
    }

    public boolean verifyPassword(String password) {
        return BCrypt.checkpw(password, this.password);
    }

    public void addProject(Project project){
        this.projects.add(project);
        project.getUsers().add(this);
        
    }


    public void addRole(Role role) {
        roles.add(role);
    }

    public void removeRole(Role role) {
        roles.remove(role);
    }

    public List<String> getRolesAsStrings() {
        return roles.isEmpty() ? null : roles.stream().map(Object::toString).collect(Collectors.toList());
    }

    @PrePersist
    private void onCreate() {
        this.createdAt = new Date();
    }

    @PreUpdate
    private void onUpdate() {
        this.updatedAt = new Date();
    }

 
}
