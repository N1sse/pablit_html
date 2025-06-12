package ac.cr.ucr.pablit_html.service;


import ac.cr.ucr.pablit_html.model.Friends;
import ac.cr.ucr.pablit_html.repository.FriendsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FriendsService {

    @Autowired
    private FriendsRepository friendsRepository;

    public Friends addFriends(Friends friends) {
        return friendsRepository.save(friends);
    }

    public List<Friends> findAllFriends() {
        return this.friendsRepository.findAll();
    }

    public Optional<Friends> findIdFriend(Integer id) {
        return this.friendsRepository.findById(id);
    }

    public void deleteFriend(Integer id) {
        this.friendsRepository.deleteById(id);
    }


}


