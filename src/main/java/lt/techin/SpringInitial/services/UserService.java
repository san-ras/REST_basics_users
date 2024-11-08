package lt.techin.SpringInitial.services;

import lt.techin.SpringInitial.dto.UserCreationDTO;
import lt.techin.SpringInitial.entities.User;
import lt.techin.SpringInitial.exceptions.UserNotFoundException;
import lt.techin.SpringInitial.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public ResponseEntity<User> getUser(Long id) {
        try {
            User user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
            return ResponseEntity.status(HttpStatus.OK).body(user);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(null);
        }
    }

    public ResponseEntity<User> addUser(UserCreationDTO userCreationDTO) {
        try {
            User user = new User();
            user.setFirstName(userCreationDTO.getFirstName());
            user.setLastName(userCreationDTO.getLastName());
            user.setEmail(userCreationDTO.getEmail());
            user.setUsername(userCreationDTO.getUsername());

            userRepository.save(user);

            return ResponseEntity.status(HttpStatus.CREATED).body(user);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(null);
        }
    }

    public ResponseEntity<String> deleteUser(Long id) {
        try {
            User user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
            userRepository.delete(user);

            return ResponseEntity.status(HttpStatus.OK).body("Success: user with id " + id + " has been deleted.");

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Something went wrong - user with id " + id + " has NOT been deleted.");
        }
    }

    public ResponseEntity<String> updateUser(Long id, UserCreationDTO userCreationDTO) {
        try {
            User user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
            user.setFirstName(userCreationDTO.getFirstName());
            user.setLastName(userCreationDTO.getLastName());
            user.setEmail(userCreationDTO.getEmail());
            user.setUsername(userCreationDTO.getUsername());

            userRepository.save(user);
            return ResponseEntity.status(HttpStatus.OK).body("Success: user with id " + id + " has been updated. " + user);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Something went wrong - user with id " + id + " has NOT been updated.");
        }
    }
}
