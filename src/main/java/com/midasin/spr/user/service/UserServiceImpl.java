package com.midasin.spr.user.service;

import com.midasin.spr.user.User;
import com.midasin.spr.user.dao.UserDAOImpl;
import com.midasin.spr.user.pagination.Criteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements IUserService{
    @Autowired
    UserDAOImpl userDAO;

    @Override
    public void userRegister(User user) {
        int result = userDAO.userInsert(user);

        if(result != 0)
            System.out.println("register:success");
        else
            System.out.println("register:fail");
    }

    @Override
    public User userSearch(User user) {
        User u = userDAO.userSelect(user);

        if(u != null)
            System.out.println("search:success");
        else
            System.out.println("search:fail");

        return u;
    }

    @Override
    public User userModify(User user) {
        int result = userDAO.userUpdate(user);

        if(result != 0)
            System.out.println("modify:success");
        else
            System.out.println("modify:fail");

        return user;
    }

    @Override
    public int userRemove(User user) {
        int result = userDAO.userDelete(user);

        if(result != 0)
            System.out.println("delete:success");
        else
            System.out.println("delete:fail");

        return result;
    }

    @Override
    public int userCount(Criteria criteria) {
        return userDAO.userCount(criteria);
    }

    @Override
    public List<User> userListup(Criteria criteria) {
        return userDAO.userListup(criteria);
    }
}
