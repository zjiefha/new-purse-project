package com.springapp.mvc.dao;

import com.springapp.mvc.bean.Users;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * Created by zhangjiefeng on 16/3/25.
 */
@Repository
public interface UsersDao {

    public int add(Users users);

    public Users find(@Param("id") int userId);

    public Users findByMobile(@Param("mobile") String mobile);

    public Integer existMobile(@Param("mobile") String mobile);

    public int update(Users users);

}
