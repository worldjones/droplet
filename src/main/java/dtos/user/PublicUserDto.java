package dtos.user;

import entities.User;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * PublicUserDto provided public information regarding a user.
 * This information could be e.g. used to display a public profile about a user.
 *
 * */

@Data
public class PublicUserDto {
    private long id;
    private String username;
    private String displayName;
    private String email;
    private int phone;
    private double billingPrHour;
    private List<String> roles;

    public PublicUserDto(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.displayName = user.getDisplayName();
        this.email = user.getEmail();
        this.phone = user.getPhone();
        this.billingPrHour = user.getBillingPrHour();
        this.roles = user.getRoles().stream().map(Object::toString).collect(Collectors.toList());
    }
}
