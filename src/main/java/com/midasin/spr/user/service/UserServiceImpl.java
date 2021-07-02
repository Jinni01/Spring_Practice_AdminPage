package com.midasin.spr.user.service;

import com.midasin.spr.user.UserVO;
import com.midasin.spr.user.dao.UserDAOImpl;
import com.midasin.spr.pagination.Criteria;
import com.midasin.spr.user.dao.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements IUserService{
    @Autowired
    UserDAOImpl userDAO;

    // @Resource(name="userMapper")
    @Autowired
    UserMapper mapper;

    @Autowired
    private BCryptPasswordEncoder pwEncoder;

    @Override
    public void userRegister(UserVO user) {
        //int result = userDAO.userInsert(user);
        user.setUserPW(pwEncoder.encode(user.getUserPW()));
        int result = mapper.insertUser(user);

        if(result != 0)
            System.out.println("register:success");
        else
            System.out.println("register:fail");
    }

    @Override
    public UserVO userSearch(UserVO user) {
        //User u = userDAO.userSelect(user);
        UserVO u = mapper.selectUser(user);

        if(u != null)
            System.out.println("search:success");
        else
            System.out.println("search:fail");

        return u;
    }

    @Override
    public UserVO userSearchByNo(int no) {
        //User u = userDAO.userSelectByNo(no);
        UserVO u = mapper.selectUserByNo(no);

        if(u != null)
            System.out.println("search:success");
        else
            System.out.println("search:fail");

        return u;
    }

    @Override
    public void userModify(UserVO user) {
        //int result = userDAO.userUpdate(user);
        user.setUserPW(pwEncoder.encode(user.getUserPW()));
        int result = mapper.updateUser(user);

        if(result != 0)
            System.out.println("modify:success");
        else
            System.out.println("modify:fail");

    }

    @Override
    public int userRemove(UserVO user) {
        int result = userDAO.userDelete(user);

        if(result != 0)
            System.out.println("delete:success");
        else
            System.out.println("delete:fail");

        return result;
    }

    @Override
    public int userRemoveByNo(int no) {
        //int result = userDAO.userDeleteByNo(no);
        int result = mapper.deleteUserByNo(no);

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
    public List<UserVO> userListup(Criteria criteria) {
        return userDAO.userListup(criteria);
    }
}
