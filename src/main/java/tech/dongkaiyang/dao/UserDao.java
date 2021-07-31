package tech.dongkaiyang.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import tech.dongkaiyang.domain.Record;
import tech.dongkaiyang.domain.User;

import java.util.List;

public interface UserDao {
    /**
     * 新增用户
     */
    @Insert("insert into user (card, email, name, password, identity, rank) values (#{card}, #{email}, #{name}, #{password}, #{identity}, #{rank})")
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
     * 按用户身份证card用户
     *
     * @param user
     * @return
     */
    @Select("select * from user where card=#{card} and password=#{password}")
    User queryUser(@Param("card") String card, @Param("password") String password);

    /**
     * 查找所有用户
     *
     * @return
     */
    @Select("select name from user")
    List<String> selectUsernames();

    /**
     * 查找所有教练名单
     *
     * @return
     */
    @Select("select id, name, card, rank from user where identity=2")
    List<User> selectAvailable();

    /**
     * 管理员查找教练申请名单
     *
     * @return
     */
    @Select("select id, card, email, name from user where identity=3")
    List<User> selectRegisterTea();

    /**
     * 更改用户身份
     *
     * @param card
     * @param identity
     * @return
     */
    @Update("update user set identity=#{identity} where card=#{card}")
    boolean updateIdentity(@Param("card") String card, @Param("identity") int identity);

    /**
     * 更改教练等级
     *
     * @param card
     * @param rank
     * @return
     */
    @Update("update user set rank=#{rank} where card=#{card} and identity=2")
    boolean updateRank(@Param("card") String card,@Param("rank") int rank);

    /**
     * 身份证或邮箱是否被使用
     *
     * @param s
     * @return
     */
    @Select("SELECT count(1) FROM user WHERE card = #{s} or email=#{s}")
    int selectCardOrEmail(String s);


}
