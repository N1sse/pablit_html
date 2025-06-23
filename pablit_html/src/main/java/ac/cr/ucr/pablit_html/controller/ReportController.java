package ac.cr.ucr.pablit_html.controller;

import ac.cr.ucr.pablit_html.model.Friends;
import ac.cr.ucr.pablit_html.model.User;
import ac.cr.ucr.pablit_html.service.ReportService;
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

    @Autowired
    private ReportService reportService;

    @GetMapping
    public List<User> findAllUsers(){
        return this.reportService.findAllUsers();
    }

    @GetMapping
    public List<Friends> findAllFriends(){
        return this.reportService.findAllFriends();
    }

    @GetMapping("/{username}")
    public ResponseEntity<?> getUserByUsername(@PathVariable String username) {
        Optional<User> user = reportService.findUserByUsername(username);

        if(!user.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("El usuario con username " + username + " no fue encontrado");
        }
        return ResponseEntity.ok(user.get());
    }


    @GetMapping("/findByLevel/{level}")
    public ResponseEntity<?> getUsersByLevel(@PathVariable Integer level) {
        try {
            List<User> users = reportService.findUsersByLevel(level);

            if (users.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NO_CONTENT)
                        .body("No existen usuarios con nivel " + level);
            }

            return ResponseEntity.ok(users);

        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }





}
