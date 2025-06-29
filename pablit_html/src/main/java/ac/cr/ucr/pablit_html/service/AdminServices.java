package ac.cr.ucr.pablit_html.service;


import ac.cr.ucr.pablit_html.model.Friends;
import ac.cr.ucr.pablit_html.model.User;
import ac.cr.ucr.pablit_html.repository.FriendsRepository;
import ac.cr.ucr.pablit_html.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdminServices {

    @Autowired
    private FriendsRepository friendsRepository;
    @Autowired
    private  UserRepository userRepository;



    public User saveUser(User user) {
        return userRepository.save(user);
    }

        public List<User> findAllUsers() {
            return userRepository.findAll();
        }
        public Optional<User> findUserByUsername(String username){
            return  userRepository.findByUsername(username);
        }


    public Optional<User> findByUsernameAndPasswordAndRol(String username,String password,String rol) {
            return userRepository.findByUsernameAndPasswordAndRol(username,password,rol);
        }


        public Optional<User> findUserById(Integer id) {
            return userRepository.findById(id);
        }


        public void deleteUserById(Integer id) {
            userRepository.deleteById(id);
        }

    public User editarUser(Integer id, User userEdit) {
        Optional<User> userOp = userRepository.findById(id);
        if (userOp.isPresent()) {
            User user = userOp.get();
            user.setUsername(userEdit.getUsername());
            user.setPassword(userEdit.getPassword());
            user.setWeight(userEdit.getWeight());
            user.setLevel(userEdit.getLevel());
            user.setAge(userEdit.getAge());
            user.setSex(userEdit.getSex());
            user.setRol(userEdit.getRol());


            return userRepository.save(user);
        }
        return null;
    }


    public List<Friends> findAllFriends() {
            return friendsRepository.findAll();
        }


        public Optional<Friends> findFriendById(Integer id) {
            return friendsRepository.findById(id);
        }


        public void deleteFriendById(Integer id) {
            friendsRepository.deleteById(id);
        }
    }






