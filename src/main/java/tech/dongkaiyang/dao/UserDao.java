package tech.dongkaiyang.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
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
    List<String> selectUsernames();

    /**
     * 查找所有教练名单
     * @return
     */
    @Select("select id, name from user where identity=2")
    List<User> selectAvailable();

    /**
     * 更改用户身份
     * @param card
     * @param identity
     * @return
     */
    @Update("update identity set identity=#{identity} where card=#{card}")
    boolean updateIdentity(String card, String identity);

    /**
     * 更改教练等级
     * @param card
     * @param rank
     * @return
     */
    @Update("update rank set rank=#{rank} where card=#{card} and identity=2")
    boolean updateRank(String card, String rank);

}
