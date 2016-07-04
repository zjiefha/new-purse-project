package com.springapp.mvc.service;

import com.sohucs.services.identitymanagement.model.User;
import com.springapp.mvc.bean.Users;
import com.springapp.mvc.dao.UsersDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by zhangjiefeng on 16/3/26.
 */
@Service
public class UsersService {

    private static final Logger logger = LoggerFactory.getLogger(UsersService.class);

    @Autowired
    private UsersDao usersDao;

    /**
     * 注册接口
     *
     * @param mobile   手机号
     * @param password 密码
     * @return
     */
    public Users signUp(String mobile, String password) {
        Users users = new Users();
        users.setMobile(mobile);
        users.setPassword(password);
        int addStatus = usersDao.add(users);
        if (addStatus == 1) {
            return users;
        }
        return null;
    }

    /**
     * 登录接口
     *
     * @param mobile   手机号
     * @param password 密码
     * @return
     */
    public Users signIn(String mobile, String password) {
        Users users = getUserInfoByMobile(mobile);
        if (users == null) return null;
        if (users.getPassword().equals(password)) return users;
        return null;
    }

    /**
     * 通过用户id修改用户数据
     *
     * @param nickname
     * @param mobile
     * @param school
     * @return
     */
    public Users update(int userId, String nickname, String mobile, String school) {
        Users users = new Users();
        users.setId(userId);
        users.setMobile(mobile);
        users.setNickName(nickname);
        users.setSchool(school);
        int update = usersDao.update(users);
        if (update == 1) return users;
        return null;
    }

    /**
     * 检查手机号是否注册
     *
     * @param mobile 手机号
     * @return
     */
    public boolean existMobile(String mobile) {
        Integer integer = usersDao.existMobile(mobile);
        if (integer == null) return false;
        else return true;
    }

    /**
     * 根据id获取用户数据
     *
     * @param id 用户id
     * @return
     */
    public Users getUserInfoById(int id) {
        Users users = usersDao.find(id);
        if (users == null) return null;
        return users;
    }

    /**
     * 根据手机号获取用户信息
     *
     * @param mobile 用户手机号
     * @return
     */
    public Users getUserInfoByMobile(String mobile) {
        Users users = usersDao.findByMobile(mobile);
        if (users == null) return null;
        return users;
    }

    public String getUserPosition(int userId) {
        Users users = usersDao.find(userId);
        return users.getPosition();
    }

    public Users updateUserPosition(int userId, String position) {
        Users users = new Users();
        users.setId(userId);
        users.setPosition(position);
        int update = usersDao.update(users);
        if (update == 1) return users;
        logger.error("failed to update position for userId={},position={}", userId, position);
        return null;
    }

    public Users updateUserPassword(int userId, String password) {
        Users users = new Users();
        users.setId(userId);
        users.setPassword(password);
        int update = usersDao.update(users);
        if (update == 1) return users;
        return null;
    }

}
