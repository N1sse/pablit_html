package ac.cr.ucr.pablit_html.controller;

import ac.cr.ucr.pablit_html.model.Friends;
import ac.cr.ucr.pablit_html.service.FriendsService;
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
@RequestMapping("/api/friends")
public class FriendsController {

    @Autowired
    private FriendsService friendsService;



    @PostMapping
    public ResponseEntity<?> addFriends(@Validated @RequestBody Friends friends, BindingResult result) {

        if (result.hasErrors()) {
            Map<String, String> errors = new HashMap<>();
            for (FieldError error : result.getFieldErrors()) {
                errors.put(error.getField(), error.getDefaultMessage());
            }
            return ResponseEntity.badRequest().body(errors);
        }
        Optional<Friends> friendsOption =this.friendsService.findIdFriend(friends.getId());
        if (friendsOption.isPresent() ) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("El Amigo con el Id " + friends.getId() + " ya está registrado");
        }

        Friends friendsAdd = this.friendsService.addFriends(friends);
        return ResponseEntity.status(HttpStatus.CREATED).body(friendsAdd);
    }


    @GetMapping("/{id}")
    public ResponseEntity <?> getFriendId(@PathVariable Integer id){

        Optional<Friends> friends= this.friendsService.findIdFriend(id);
        if(!friends.isPresent()){

            return  ResponseEntity.status(HttpStatus.NOT_FOUND).body("El amigo con Id  "+id+" No existe");
        }

        return ResponseEntity.ok(friends);
    }
    @GetMapping
    public List<Friends> getAllFriends(){

        return this.friendsService.findAllFriends();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity <?> deleteFriend(@PathVariable Integer id){
        Optional<Friends> friendsOption =this.friendsService.findIdFriend(id);
        if(!friendsOption.isPresent()){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("no se puede eliminar el amigo ya que no existe el id  : "+id) ;
        }
        this.friendsService.deleteFriend(id);
        return ResponseEntity.noContent().build();
    }
}