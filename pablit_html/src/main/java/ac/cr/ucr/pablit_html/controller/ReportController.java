package ac.cr.ucr.pablit_html.controller;

import ac.cr.ucr.pablit_html.model.User;
import ac.cr.ucr.pablit_html.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/reports")
public class ReportController {
/*
    @Autowired
    private UserService userService;

    @GetMapping
    public List<User> getAllUser(){
        return this.userService.findAllUser();

    }

    @GetMapping("/reportUser")
    public ResponseEntity<?> getUserReport() {
        try {
            Map<String, Object> report = new HashMap<>();

           // report.put("users", userService.findAllUser());

            return ResponseEntity.ok(report);

        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                    .body(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity <?> getUser(@PathVariable Integer id){
        Optional<User> user= this.userService.findByIDUser(id);
        if(user==null || user.get().getId()==0 ){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("La id "+id+" que usted ingresó no existe");

        }

        return ResponseEntity.ok(user);
    }

    @GetMapping("/users/level/{level}")
    public ResponseEntity<?> getUsersByLevel(@PathVariable Integer level) {
        try {
            List<User> users = userService.findByLevel(level);

            if(users.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("No hay usuarios de nivel " + level);
            }

            return ResponseEntity.ok(users);

        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                    .body(e.getMessage());
        }
    }


    //// eesto debe de ir en el service User
    //public List<User> findByLevel(Integer level) {
    //    // Implementación dependerá de tu repositorio/JPA
    //    return userRepository.findByLevel(level);
    //}

    //eesto en el repository de User
//    @Repository
//    public interface UserRepository extends JpaRepository<User, Integer> {
//        List<User> findByLevel(Integer level);

*/
}
