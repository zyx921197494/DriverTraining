package tech.dongkaiyang.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import tech.dongkaiyang.domain.User;

import java.util.List;

public interface UserDao {
    /**
     * 新增用户
     */
    @Insert("insert into user (name,password) values (#{name},#{password})")
    public void insertUser(User user);

    /**
     * 按用户名查询用户
     *
     * @param name
     * @return
     */
    @Select("select * from user where name=#{name}")
    User queryUserByName(String name);

    /**
     * 按用户名密码查询用户
     *
     * @param user
     * @return
     */
    @Select("select * from user where name=#{name} and password=#{password}")
    User queryUser(User user);

    /**
     * 查找所有用户
     *
     * @return
     */
    @Select("select name from user")
    List<String> findUsernames();

    @Select("select id, name from user where identity=2")
    List<User> finaAvailable();
}
