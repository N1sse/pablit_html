package ac.cr.ucr.pablit_html.service;


import ac.cr.ucr.pablit_html.model.Friends;

import ac.cr.ucr.pablit_html.model.User;
import ac.cr.ucr.pablit_html.repository.FriendsRepository;
import ac.cr.ucr.pablit_html.repository.RequestRepository;
import ac.cr.ucr.pablit_html.repository.UserRepository;
import ac.cr.ucr.pablit_html.repository.FriendsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FriendsService {

    @Autowired
    private FriendsRepository friendsRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RequestRepository requestRepository;

    public Friends addFriend(Friends friend) {
        // Validar que el User exista
        Integer userId = friend.getUser().getId();

        userRepository.findById(userId).orElseThrow(() ->
                new RuntimeException("El usuario con ID " + userId + " no existe."));

        // Validar que el Request exista
        Integer requestId = friend.getRequest().getId();
        requestRepository.findById(requestId).orElseThrow(() ->
                new RuntimeException("La solicitud (request) con ID " + requestId + " no existe."));

        return friendsRepository.save(friend);
    }

    public Optional<Friends> findFriendById(Integer id) {
        return friendsRepository.findById(id);
    }

    public List<Friends> findAllFriends() {

        return friendsRepository.findAll();
    }

    public void deleteFriend(Integer id) {

        friendsRepository.deleteById(id);
    }
    public boolean friendExist(User sender, User receiver) {
        return friendsRepository.findByUserAndRequest_Sender(receiver, sender).isPresent();
    }


}


