package ac.cr.ucr.pablit_html.repository;

import ac.cr.ucr.pablit_html.model.Friends;
import ac.cr.ucr.pablit_html.model.Request;
import ac.cr.ucr.pablit_html.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RequestRepository extends JpaRepository<Request, Integer> {

    void deleteAllBySender(User sender);
    void deleteAllByReceiver(User receiver);
}
