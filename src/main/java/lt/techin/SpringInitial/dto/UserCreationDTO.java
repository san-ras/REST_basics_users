package lt.techin.SpringInitial.dto;

import lombok.Data;

@Data
public class UserCreationDTO {

    private String username;
    private String firstName;
    private String lastName;
    private String email;
}
