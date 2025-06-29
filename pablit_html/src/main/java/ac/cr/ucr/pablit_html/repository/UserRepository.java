package ac.cr.ucr.pablit_html.repository;

import ac.cr.ucr.pablit_html.model.User;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.data.jpa.repository.JpaRepository;

<<<<<<< HEAD
=======
import java.util.List;
>>>>>>> origin/jimena_egly
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer>
{
<<<<<<< HEAD
    Optional<User> findByUsername (String username);
    Optional<User> findByUsernameAndPasswordAndRol (String username, String password,String rol);

    Optional<User> findByUsernameAndPassword(String username, String password);
=======
    Optional<User> findByUsername(String username);

    List<User> findByLevel(Integer level);
>>>>>>> origin/jimena_egly

}
