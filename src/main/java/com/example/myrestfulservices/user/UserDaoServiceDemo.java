package com.example.myrestfulservices.user;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

@Component
public class UserDaoServiceDemo {
    private static List<UserV4> users = new ArrayList<>();

    private static int usersCount = 3;

    static {
        users.add(new UserV4(1, "Kenneth", new Date(), "test1", "701010-1111111"));
        users.add(new UserV4(2, "Alice", new Date(), "test2", "801111-2222222"));
        users.add(new UserV4(3, "Elena", new Date(), "test3", "901313-1111111"));
    }

    public List<UserV4> findAll() {
        return users;
    }

    public UserV4 save(UserV4 user) {
        if (user.getId() == null) {
            user.setId(++usersCount);
        }

        users.add(user);
        return user;
    }

    public UserV4 findOne(int id) {
        for (UserV4 user : users) {
            if (user.getId() == id) {
                return user;
            }
        }

//        return null;
        throw new UserNotFoundException2("User not found");
    }

    public UserV4 deleteById(int id) {
        Iterator<UserV4> iterator = users.iterator();

        while (iterator.hasNext()) {
            UserV4 user = iterator.next();

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
    public UserV4 updateByName(int id, String name){
        UserV4 user = findOne(id);
        user.setName(name);
        user.setJoinDate(new Date());

        return user;
    }
}
