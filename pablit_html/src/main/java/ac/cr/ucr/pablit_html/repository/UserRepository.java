package ac.cr.ucr.pablit_html.repository;

import ac.cr.ucr.pablit_html.model.User;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer>
{
    Optional<User> findByUsername (String username);
    Optional<User> findByUsernameAndPasswordAndRol (String username, String password,String rol);

    Optional<User> findByUsernameAndPassword(String username, String password);

}
