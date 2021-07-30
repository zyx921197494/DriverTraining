package tech.dongkaiyang.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.dongkaiyang.dao.RecordDao;
import tech.dongkaiyang.dao.UserDao;
import tech.dongkaiyang.domain.Record;
import tech.dongkaiyang.domain.User;
import tech.dongkaiyang.service.UserService;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private RecordDao recordDao;

    @Override
    public List<Record> searchRecord(String card) {
        return recordDao.selectUnaccepted(card);
    }

    @Override
    public boolean changeIdentity(String card, int identity) {
        return userDao.updateIdentity(card,identity);
    }

    @Override
    public boolean changeRank(String card, int rank) {
        return userDao.updateRank(card, rank);
    }

    @Override
    public boolean insertCode(String code) {
        return false;
    }

    @Override
    public boolean validateCode(String code) {
        return false;
    }

    @Override
    public boolean deleteCode(String code) {
        return false;
    }

    /**
     * 是否存在身份证或邮箱
     *
     * @param s
     * @return
     */
    @Override
    public int verify(String s) {
        return userDao.selectCardOrEmail(s);
    }

    @Override
    public User queryUser(User user) {
        User u = userDao.queryUser(user);
        return u;
    }

    @Override
    public User queryUserId(String name) {
        return userDao.queryUserByName(name);
    }

    @Override
    public boolean isExistUser(String name) {
        User user = userDao.queryUserByName(name);
        return user != null;
    }

    @Override
    public boolean insertUser(User user) {
        try {
            userDao.insertUser(user);
            return true;
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }

    @Override
    public List<String> findAllUserNames() {
        return userDao.selectUsernames();
    }


    @Override
    public List<User> findAvailable() {
        return userDao.selectAvailable();
    }

    @Override
    public List<User> findRegisterTea() {
        return userDao.selectRegisterTea();
    }
}
