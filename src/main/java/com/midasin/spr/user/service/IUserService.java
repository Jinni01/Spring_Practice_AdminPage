package com.midasin.spr.user.service;

import com.midasin.spr.user.User;
import com.midasin.spr.user.pagination.Criteria;

import java.util.List;

public interface IUserService {
    void userRegister(User user);
    User userSearch(User user);
    User userModify(User user);
    int userRemove(User user);
    int userCount(Criteria criteria);
    List<User> userListup(Criteria criteria);
}
