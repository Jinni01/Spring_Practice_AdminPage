package com.midasin.spr.user.service;

import com.midasin.spr.user.UserVO;
import com.midasin.spr.pagination.Criteria;

import java.util.List;

public interface IUserService {
    void userRegister(UserVO user);
    UserVO userSearch(UserVO user);
    UserVO userSearchByNo(int no);
    void userModify(UserVO user);
    int userRemove(UserVO user);
    int userRemoveByNo(int no);
    int userCount(Criteria criteria);
    List<UserVO> userListup(Criteria criteria);
}
