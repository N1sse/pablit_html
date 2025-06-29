package ac.cr.ucr.pablit_html.controller;

import ac.cr.ucr.pablit_html.model.DTO.LevelUpDTO;
import ac.cr.ucr.pablit_html.model.DTO.UserLoginDTO;
import ac.cr.ucr.pablit_html.model.User;
import ac.cr.ucr.pablit_html.service.UserService;
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
@RequestMapping("api/users")
public class UserController
{
    @Autowired
    UserService userService;


    @GetMapping("/FindUsername/{username}")
    public ResponseEntity<?> getUser(@PathVariable String username)
    {
        Optional<User> user =  this.userService.findByUsername(username);
        if(!user.isPresent())
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("El usuario con el usuario " + username + " no fue encontrado");
        }
        return ResponseEntity.ok(user);
    }

    @GetMapping("/userById/{id}")
    public ResponseEntity<?> getUser(@PathVariable Integer id)
    {
        Optional<User> user =  this.userService.findByIDUser(id);
        if(!user.isPresent())
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("El usuario con el id " + id + " No fue encontrado");
        }
        return ResponseEntity.ok(user);
    }

    @PostMapping
    public ResponseEntity<?> addUser(@Validated @RequestBody User user, BindingResult result)
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

        Optional<User> userOp=this.userService.findByUsername(user.getUsername());
        if(userOp.isPresent())
        {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("El username "+ user.getUsername()+" ya se encuentra en uso");
        }

        user.setId(null);
        user.setLevel(1);
        user.setRol("user");
        User userAdd=this.userService.saveUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(userAdd);
    }

    @PutMapping("/editByID/{id}")
    public ResponseEntity<?> editUser(@Validated @PathVariable Integer id,@RequestBody User userEdit, BindingResult result)
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

        Optional<User> userOp=this.userService.findByIDUser(id);
        if(userOp.isPresent())
        {
            if(id!=userEdit.getId())
            {
                return ResponseEntity.status(HttpStatus.CONFLICT).body("EL id de busqueda no es igual al ID del objeto");
            }
            else
            {
                return ResponseEntity.ok(this.userService.putUser(id, userEdit));
            }
        }
        return ResponseEntity.status(HttpStatus.CONFLICT).body("El usuario con el ID "+id+" no se encuentra registrado");
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserLoginDTO loginDTO) {
        Optional<User> userOp = userService.loginByUsername(loginDTO.getUsername(), loginDTO.getPassword());
        if (userOp.isPresent()) {
            User user = userOp.get();
            Map<String, Object> response = new HashMap<>();
            response.put("id", user.getId());
            response.put("username", user.getUsername());
            response.put("level", user.getLevel());
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciales invalidas");
        }
    }

    @PutMapping("/levelUp/{id}")
    public ResponseEntity<?> levelUp(@Validated @PathVariable Integer id, @RequestBody LevelUpDTO levelUpDTO, BindingResult result) {
        if (result.hasErrors()) {
            Map<String, String> errors = new HashMap<>();
            for (FieldError error : result.getFieldErrors()) {
                errors.put(error.getField(), error.getDefaultMessage());
            }
            return ResponseEntity.badRequest().body(errors);
        }

        Optional<User> userOp = userService.findByIDUser(id);
        if (userOp.isPresent()) {
            if (!id.equals(levelUpDTO.getId())) {
                return ResponseEntity.status(HttpStatus.CONFLICT)
                        .body("El id de búsqueda no coincide con el ID del DTO");
            } else {
                User userUpdated = userService.levelUp(id);
                return ResponseEntity.ok(userUpdated);
            }
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("El usuario con el ID " + id + " no fue encontrado");
    }
}
