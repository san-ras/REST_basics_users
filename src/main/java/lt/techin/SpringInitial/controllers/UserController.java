package lt.techin.SpringInitial.controllers;

import lt.techin.SpringInitial.dto.UserCreationDTO;
import lt.techin.SpringInitial.entities.User;
import lt.techin.SpringInitial.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping()
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUser(@PathVariable Long id) {
        return userService.getUser(id);
    }

    @PostMapping("/add")
    public ResponseEntity<User> addUser(@RequestBody UserCreationDTO userCreationDTO) {
        return userService.addUser(userCreationDTO);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
        return userService.deleteUser(id);
    }

    @PutMapping("{id}")
    public ResponseEntity<String> updateUser(@PathVariable Long id, @RequestBody UserCreationDTO userCreationDTO) {
        return userService.updateUser(id, userCreationDTO);
    }
}
