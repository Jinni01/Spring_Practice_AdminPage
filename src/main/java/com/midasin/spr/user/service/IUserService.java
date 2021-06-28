package com.midasin.spr.user.service;

import com.midasin.spr.user.User;
import com.midasin.spr.pagination.Criteria;

import java.util.List;

public interface IUserService {
    void userRegister(User user);
    User userSearch(User user);
    User userSearchByNo(int no);
    void userModify(User user);
    int userRemove(User user);
    int userRemoveByNo(int no);
    int userCount(Criteria criteria);
    List<User> userListup(Criteria criteria);
}
