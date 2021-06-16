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
public class ProjectHours implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String hoursSpent;
    private String userStory;
    private String description;
   
    
   @ManyToOne(cascade = CascadeType.PERSIST)
   private User records;
    
   @ManyToOne(cascade = CascadeType.PERSIST)
   private Project billedBy;
    
    public ProjectHours(String hoursSpent, String userStory, String description) {
        this.hoursSpent = hoursSpent;
        this.userStory = userStory;
        this.description = description;
    }
    
   
    
    
    
}