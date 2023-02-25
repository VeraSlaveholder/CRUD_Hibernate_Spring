package web.service;

import web.models.User;

import java.util.List;

public interface UserService {
    List<User> allUsers();

    User show(int id);

    void save(User user);

    void update(int id, User updatedUser);

    void delete(int id);
}
