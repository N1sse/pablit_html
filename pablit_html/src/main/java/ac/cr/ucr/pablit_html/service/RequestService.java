package ac.cr.ucr.pablit_html.service;

import ac.cr.ucr.pablit_html.model.Friends;
import ac.cr.ucr.pablit_html.model.Request;
import ac.cr.ucr.pablit_html.repository.FriendsRepository;
import ac.cr.ucr.pablit_html.repository.RequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RequestService {

    @Autowired
    private RequestRepository requestRepository;

    @Autowired
    private FriendsRepository friendsRepository;

    public RequestService(RequestRepository requestRepository, FriendsRepository friendsRepository) {
        this.requestRepository = requestRepository;
        this.friendsRepository = friendsRepository;
    }

    public Request makeRequest(Request request) {
        int friendCount = 0;
        // Lista de amigos vinculados a esta solicitud
        List<Friends> friendsList = new ArrayList<>();

        for (Friends friend : request.getFriends()) {
            Friends existFriend = friendsRepository.findById(friend.getId())
                    .orElseThrow(() -> new RuntimeException("Friend con ID " + friend.getId() + " no existe"));

            existFriend.setRequest(request);
            friendsList .add(existFriend);
        }

        request.setFriends(friendsList);

        return requestRepository.save(request);
    }

    public List<Request> getAllRequests() {
        return requestRepository.findAll();
    }
}
