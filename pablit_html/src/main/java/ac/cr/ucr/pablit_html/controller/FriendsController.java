package ac.cr.ucr.pablit_html.controller;

<<<<<<< HEAD

import ac.cr.ucr.pablit_html.model.DTO.FriendsDTO;
import ac.cr.ucr.pablit_html.model.Friends;
import ac.cr.ucr.pablit_html.model.Request;
import ac.cr.ucr.pablit_html.model.User;
import ac.cr.ucr.pablit_html.service.FriendsService;
import ac.cr.ucr.pablit_html.service.RequestService;
import ac.cr.ucr.pablit_html.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
=======
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
>>>>>>> origin/jimena_egly
import java.util.Optional;

@RestController
@RequestMapping("/api/friends")
public class FriendsController {

    @Autowired
    private FriendsService friendsService;
<<<<<<< HEAD
    @Autowired
    private UserService userService;
    @Autowired
    private RequestService requestService;

    @PostMapping
      public ResponseEntity<?> addFriendByUsername(@RequestBody FriendsDTO dto) {
        try {
            if (dto.getSenderUsername() == null || dto.getReceiverUsername() == null) {
                  return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Debe enviar ambos usernames.");
            }

            Optional<User> senderOpt = userService.findByUsername(dto.getSenderUsername());

             Optional<User> receiverOpt = userService.findByUsername(dto.getReceiverUsername());

             if (senderOpt.isEmpty() || receiverOpt.isEmpty()) {
                 return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Uno o ambos usuarios no existen.");
             }

            User sender = senderOpt.get();
            User receiver = receiverOpt.get();

            if (sender.getId() == receiver.getId()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No puede enviarse una solicitud a sí mismo.");
            }

            //valida si ya son amigos
            boolean alreadyFriends = friendsService.friendExist(sender, receiver);
              if (alreadyFriends) {
                return ResponseEntity.status(HttpStatus.CONFLICT).body("Estos usuarios ya son amigos.");
             }
            //crea un nuevo request y acepta la amistad
            Request request = new Request();
             request.setSender(sender);
             request.setReceiver(receiver);
            request.setStatus("aceptada");
             request.setFriendCount(1);
            request.setFriends(new ArrayList<>());
            //Guarda el request
            request = requestService.makeRequest(request);

            Friends friend = new Friends();
            friend.setUser(receiver);
            friend.setRequest(request);
            //Guarda al amigo
            Friends saved = friendsService.addFriend(friend);

            return ResponseEntity.status(HttpStatus.CREATED).body(saved);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al procesar la solicitud: " + e.getMessage());
        }
=======



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
>>>>>>> origin/jimena_egly
    }


    @GetMapping("/{id}")
<<<<<<< HEAD
    public ResponseEntity<?> getFriendById(@PathVariable Integer id) {
        try {

            Optional<Friends> friend = friendsService.findFriendById(id);

            if (friend.isEmpty()) {

                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("El amigo con ID " + id + " no existe.");
            }

            return ResponseEntity.ok(friend.get());

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al obtener el amigo: " + e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<?> getAllFriends() {
        try {
            List<Friends> all = friendsService.findAllFriends();

            if (all.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NO_CONTENT).body("No hay amigos registrados.");
            }

            List<FriendsDTO> friendsList = new ArrayList<>();

            for (Friends friend : all) {
                friendsList.add(new FriendsDTO(
                        friend.getRequest().getSender().getUsername(),
                        friend.getRequest().getReceiver().getUsername(),
                        friend.getId()
                ));
            }

            return ResponseEntity.ok(friendsList);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al obtener los amigos: " + e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteFriend(@PathVariable Integer id) {
        try {
            Optional<Friends> friend = friendsService.findFriendById(id);

            if (friend.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se puede eliminar el amigo con ID " + id + " porque no existe.");
            }

               friendsService.deleteFriend(id);
              return ResponseEntity.noContent().build();

          } catch (Exception e) {
              return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body("Error al eliminar el amigo: " + e.getMessage());
        }
      }
}
=======
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
>>>>>>> origin/jimena_egly
