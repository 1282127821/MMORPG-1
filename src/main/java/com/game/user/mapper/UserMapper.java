package com.game.user.mapper;

import com.game.user.bean.User;

/**
 * @ClassName UserMapper
 * @Description 用户mapper
 * @Author DELL
 * @Date 2019/5/2716:53
 * @Version 1.0
 */
public interface UserMapper {
    /**
     * 通过id获取用户user
     * @param id id
     * @return User
     */
    public User selectUserById(Integer id);

    /**
     * 通过username获取用户user
     * @param username username
     * @return User
     */
    public User selectUserByUsername(String username);

    /**
     * 通过用户username获取游戏角色role
     * @param username username
     * @return int
     */
    public Integer getUserRoleIdByUsername(String username);

    /**
     * 创建用户user
     * @param user user
     * @return true or false
     */
    public boolean addUser(User user);

    /**
     * 检查
     * @param username username
     * @return User
     */
    public User checkUser(String username);

    /**
     * 更新用户信息
     * @param user user
     */
    public void updateUser(User user);
}