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
@Table(name = "projects")
public class Project implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String description;
    
    
   
   @ManyToMany(cascade = CascadeType.PERSIST)
   private List<User> users;
   
   @OneToMany(cascade = CascadeType.PERSIST)
   private List<ProjectHours> projectHours;
   
   public Project(String name, String description) {
        this.name = name;
        this.description = description;
        this.users = new ArrayList<>(); 
        this.projectHours = new ArrayList<>();
    }
    
   
    
    
    
}