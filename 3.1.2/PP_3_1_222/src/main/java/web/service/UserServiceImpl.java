package web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import web.model.MyUser;
import web.repository.UserRepository;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public MyUser passwordCoder(MyUser myUser) {
        myUser.setPassword(passwordEncoder.encode(myUser.getPassword()));
        return myUser;
    }

    public List<MyUser> allUsers() {
        return userRepository.findAll();
    }

    @Override
    public MyUser getUserById(Long id) {
        return userRepository.getById(Math.toIntExact((Long) id));
    }

    public void addUser(MyUser myUser) {
        userRepository.save(passwordCoder(myUser));
    }

    @Override
    public void updateUser(MyUser myUser) {
        MyUser myUserToBeUpdated = userRepository.getById(Math.toIntExact(myUser.getId()));
        myUserToBeUpdated.setUsername(myUser.getUsername());
        myUserToBeUpdated.setSurname(myUser.getSurname());
        myUserToBeUpdated.setAge(myUser.getAge());
        myUserToBeUpdated.setRoles(myUser.getRoles());
        userRepository.flush();
    }

    @Override
    public void deleteUserById(Long id) {
        userRepository.deleteById(Math.toIntExact(id));
    }
}