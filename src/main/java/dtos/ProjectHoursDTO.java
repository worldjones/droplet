package dtos;


import entities.User;
import entities.Project;
import entities.ProjectHours;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;




@AllArgsConstructor
@Data
public class ProjectHoursDTO {
    private long id;
    private String hoursSpent;
    private String userStory;
    private String description;
    
    public ProjectHoursDTO (ProjectHours projectHours){
        this.id = projectHours.getId();
        this.hoursSpent = projectHours.getHoursSpent();
        this.userStory = projectHours.getUserStory();
        this.description = projectHours.getDescription();
        
    
}
    
}
