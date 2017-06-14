package com.dabai.service;

import com.dabai.dao.UserDao;
import com.dabai.domain.User;
import com.dabai.utils.MD5Utils;
import org.springframework.transaction.annotation.Transactional;

/**
 * 用户的业务层
 */
@Transactional
public class UserServiceImpl implements UserService {

    private UserDao userDao;
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    /**
     * 通过登录名进行验证
     * @param user_code
     * @return
     */
    public User checkCode(String user_code) {
        return userDao.checkCode(user_code);
    }

    /**
     * 保存用户,密码需要加密
     * @param user
     */
    public void save(User user) {
        String password = user.getUser_password();
        //给密码加密
        user.setUser_password(MD5Utils.md5(password));
        //用户的状态默认是1状态
        user.setUser_state("1");
        //调用持久层
        userDao.save(user);
    }

    /**
     * 登录,通过登录名和密码做校验
     * 先给密码加密,再查询
     * @param user
     * @return
     */
    public User login(User user) {
        String password = user.getUser_password();
        //给密码加密
        user.setUser_password(MD5Utils.md5(password));
        //查询
        return userDao.login(user);
    }
}
