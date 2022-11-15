package com.example.myrestfulservices.user;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

@Component
public class UserDaoService {
    private static List<User> users = new ArrayList<>();

    private static int usersCount = 3;

    static {
        users.add(new User(1, "Kenneth", new Date(), "test1", "701010-1111111", "Seoul"));
        users.add(new User(2, "Alice", new Date(), "test2", "801111-2222222"));
        users.add(new User(3, "Elena", new Date(), "test3", "901313-1111111"));
    }

    public List<User> findAll() {
        return users;
    }

    public User save(User user) {
        if (user.getId() == null) {
            user.setId(++usersCount);
        }

        users.add(user);
        return user;
    }

    public User findOne(int id) {
        for (User user : users) {
            if (user.getId() == id) {
                return user;
            }
        }

        return null;
    }

    public User deleteById(int id) {
        Iterator<User> iterator = users.iterator();

        while (iterator.hasNext()) {
            User user = iterator.next();

            if (user.getId() == id) {
                iterator.remove();
                return user;
            }
        }

        return null;
    }

    /*
     * UserDaoService.java
     * Update method with a ID, NAME
     */
    public User updateByName(int id, String name){
        User user = findOne(id);
        user.setName(name);
        user.setJoinDate(new Date());

        return user;
    }
}
