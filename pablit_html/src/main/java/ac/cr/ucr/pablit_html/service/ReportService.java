package ac.cr.ucr.pablit_html.service;

import ac.cr.ucr.pablit_html.model.Friends;
import ac.cr.ucr.pablit_html.repository.FriendsRepository;
import ac.cr.ucr.pablit_html.repository.UserRepository;
import ac.cr.ucr.pablit_html.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public class ReportService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private FriendsRepository friendsRepository;

    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    public List<Friends> findAllFriends() {
        return friendsRepository.findAll();
    }

    public Optional<User> findUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public List<User> findUsersByLevel(Integer level) {
        return userRepository.findByLevel(level);
    }


}