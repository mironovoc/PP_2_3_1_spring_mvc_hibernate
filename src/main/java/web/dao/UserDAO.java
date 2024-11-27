package web.dao;

import org.springframework.stereotype.Component;
import web.models.User;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserDAO {
    private static int USERS_COUNT;
    private List<User> users;

    {
        users = new ArrayList<User>();

        users.add(new User(++USERS_COUNT, "Miron"));
        users.add(new User(++USERS_COUNT, "Andreq"));
    }

    public List<User> index() {
        return users;
    }

    public User show(int id) {
        return users.stream().filter(user -> user.getId() == id).findAny().orElse(null);
    }

    public void save(User user) {
        user.setId(++USERS_COUNT);
        users.add(user);
    }
}
