package ac.cr.ucr.pablit_html.service;

import ac.cr.ucr.pablit_html.model.User;
import ac.cr.ucr.pablit_html.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService
{

    @Autowired
    private UserRepository userRepository;


    public User saveUser(User user) {
        return userRepository.save(user);
    }


    public Optional<User> findByIDUser(Integer id) {
        return userRepository.findById(id);
    }


    public User putUser(Integer id, User userEdit) {
        Optional<User> userOp = this.userRepository.findById(id);
        if(userOp.isPresent())
        {
            userEdit.setPassword(userOp.get().getPassword());
            userEdit.setLevel(userOp.get().getLevel());

            User user = userOp.get();
            user=userEdit;
            return this.userRepository.save(user);
        }
        return null;
    }
    public Optional<User> findByUsername(String username)
    {
        return userRepository.findByUsername(username);
    }



}
