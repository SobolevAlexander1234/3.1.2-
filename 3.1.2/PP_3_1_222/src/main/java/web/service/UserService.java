package web.service;

import web.model.MyUser;

import java.util.List;

public interface UserService {
    List<MyUser> allUsers ();
    MyUser getUserById (Long id);
    void addUser (MyUser myUser);
    void deleteUserById (Long id);
    void updateUser (MyUser myUser);
    MyUser passwordCoder(MyUser myUser);
}