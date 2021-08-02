package tech.dongkaiyang.service;

import tech.dongkaiyang.domain.Record;
import tech.dongkaiyang.domain.User;

import java.util.List;

public interface RecordService {

    /**
     * 新增一条学习记录
     *
     * @param record
     * @return
     */
    boolean insertRecord(Record record);

    /**
     * 学员和教练根据用户名查询所属所有记录
     *
     * @param card
     * @return
     */
    List<Record> findUserRecords(String card);

    /**
     * 管理员查看已有所有学员学习记录
     *
     * @return
     */
    List<Record> findAllStuRecords(String stuName);

    /**
     * 管理员查看已有所有教练上课记录
     *
     * @return
     */
    List<Record> findAllTeaRecords(String teaName);

    List<Record> findStuRecords();

//    List<Record> findTeaRecords();

    /**
     * 更新record状态
     *
     * @param id
     * @return
     */
    boolean updateStatus(int status,int id);

}
