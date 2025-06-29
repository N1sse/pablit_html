package ac.cr.ucr.pablit_html.controller;


import ac.cr.ucr.pablit_html.model.DTO.AdminLoginDTO;
import ac.cr.ucr.pablit_html.model.Friends;
import ac.cr.ucr.pablit_html.model.User;
import ac.cr.ucr.pablit_html.service.AdminServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    @Autowired
    private AdminServices adminServices;





    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = adminServices.findAllUsers();
        if (users.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(users);
    }



    @GetMapping("/users/username/{username}")
    public ResponseEntity<User> getUserByUsername(@PathVariable String username) {
        Optional<User> userOpt = adminServices.findUserByUsername(username);
        return userOpt.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<?> addAdmin(@Validated @RequestBody User user, BindingResult result)
    {
        if(result.hasErrors())
        {
            Map<String, String> errors = new HashMap<>();
            for(FieldError error: result.getFieldErrors())
            {
                errors.put(error.getField(),error.getDefaultMessage());
            }
            return ResponseEntity.badRequest().body(errors);
        }

        Optional<User> userOp = this.adminServices.findUserByUsername(user.getUsername());
        if(userOp.isPresent())
        {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("El username "+ user.getUsername()+" ya se encuentra en uso");
        }

        user.setRol("admin");
        User userAdd = this.adminServices.saveUser(user); //
        return ResponseEntity.status(HttpStatus.CREATED).body(userAdd);
    }


    @PostMapping("/login")
    public ResponseEntity<?> loginAdmin(@RequestBody AdminLoginDTO loginDTO) {
        Optional<User> admin = adminServices.findByUsernameAndPasswordAndRol(
                loginDTO.getUsername(),
                loginDTO.getPassword(),
                "admin" // Se fija que sea admin, no lo deja al usuario
        );

        if (admin.isPresent()) {
            return ResponseEntity.ok(admin.get());
        } else {
            return ResponseEntity.status(401).body("Credenciales incorrectas o no eres administrador.");
        }
    }



    @GetMapping("/users/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Integer id) {
        Optional<User> userOpt = adminServices.findUserById(id);
        return userOpt.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }


    @DeleteMapping("/users/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Integer id) {
        Optional<User> userOpt = adminServices.findUserById(id);
        if (userOpt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        adminServices.deleteUserById(id);
        return ResponseEntity.noContent().build();
    }


    @PutMapping("/users/{id}")
    public ResponseEntity<?> updateUser(@PathVariable Integer id, @RequestBody User userEdit) {
        User updated = adminServices.editarUser(id, userEdit);
        if (updated == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updated);
    }



    @GetMapping("/friends")
    public ResponseEntity<List<Friends>> getAllFriends() {
        List<Friends> friends = adminServices.findAllFriends();
        if (friends.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(friends);
    }


    @GetMapping("/friends/{id}")
    public ResponseEntity<Friends> getFriendById(@PathVariable Integer id) {
        Optional<Friends> friendOpt = adminServices.findFriendById(id);
        return friendOpt.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }


    @DeleteMapping("/friends/{id}")
    public ResponseEntity<Void> deleteFriendById(@PathVariable Integer id) {
        Optional<Friends> friendOpt = adminServices.findFriendById(id);
        if (friendOpt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        adminServices.deleteFriendById(id);
        return ResponseEntity.noContent().build();
    }
}