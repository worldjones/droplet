package dtos;


import entities.User;
import entities.Project;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;



@AllArgsConstructor
@Data
public class ProjectDTO {
    private long id;
    private String name;
    private String description;
    
    
    public ProjectDTO (Project project){
        this.id = project.getId();
        this.name = project.getName();
        this.description = project.getDescription();
        
    }
    
}
