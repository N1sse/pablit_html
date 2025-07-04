package ac.cr.ucr.pablit_html.repository;

import ac.cr.ucr.pablit_html.model.Friends;
import ac.cr.ucr.pablit_html.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FriendsRepository extends JpaRepository<Friends, Integer> {
    Optional<Friends> findByUserAndRequest_Sender(User receiver, User sender);
    void deleteAllByUser(User user);

}