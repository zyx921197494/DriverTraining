package tech.dongkaiyang.service;

import tech.dongkaiyang.domain.User;

import java.util.List;

public interface UserService {

    /**
     * 验证用户名密码
     *
     * @param user
     * @return
     */
    boolean queryUser(User user);

    /**
     * 由姓名查找用户ID
     * @param name
     * @return
     */
    User queryUserId(String name);

    /**
     * 验证用户名是否可用
     *
     * @param name
     * @return
     */
    boolean isExistUser(String name);

    /**
     * 注册新用户
     *
     * @param user
     */
    boolean insertUser(User user);

    /**
     * 查找所有用户的名字
     *
     * @return
     */
    List<String> findAllUserNames();

    /**
     * 查找可用教练的名字
     *
     * @return
     */
    List<User> findAvailable();
}
