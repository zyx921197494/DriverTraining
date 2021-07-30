package tech.dongkaiyang.service;

import tech.dongkaiyang.domain.Record;
import tech.dongkaiyang.domain.User;

import java.util.List;

public interface UserService {

    /**
     * 根据教练查询其未审核请求
     * @param card
     * @return
     */
    List<Record> searchRecord(String card);

    /**
     * 改变用户identity(管理员接收教练注册申请)
     * @param card
     * @param identity
     * @return
     */
    boolean changeIdentity(String card, int identity);

    /**
     * 用教练身份证改变教练等级rank
     *
     * @param card
     * @param rank
     * @return
     */
    boolean changeRank(String card, int rank);

    /**
     * 插入一条验证码
     *
     * @param code
     * @return
     */
    boolean insertCode(String code);

    /**
     * 校验验证码是否合法
     *
     * @param code
     * @return
     */
    boolean validateCode(String code);

    /**
     * 使验证码失效
     *
     * @param code
     * @return
     */
    boolean deleteCode(String code);

    /**
     * 验证身份证或邮箱是否可用
     * @param s
     * @return
     */
    int verify(String s);

    /**
     * 验证用户名密码
     *
     * @param user
     * @return
     */
    User queryUser(User user);

    /**
     * 由姓名查找用户ID
     *
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

    /**
     * 查看未审核的注册教练
     * @return
     */
    List<User> findRegisterTea();
}
