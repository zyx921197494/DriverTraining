package tech.dongkaiyang.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import tech.dongkaiyang.domain.Record;
import tech.dongkaiyang.domain.User;

import java.time.LocalDateTime;
import java.util.List;

public interface RecordDao {
    /**
     * 新增学习记录
     *
     * @param record
     * @return
     */
    @Insert("insert into record (stu_card, tea_card, start_time, end_time , location, status) values (#{stuCard}, #{teaCard}, #{startTime}, #{endTime}, #{location}, #{status})")
    boolean insertRecord(Record record);

    /**
     * 由用户card查询记录
     *
     * @param card
     * @return
     */
    @Select("select r.id, r.stu_card, r.tea_card, r.start_time, r.end_time, r.location, r.status from record as r where stu_card=#{card} or tea_card=#{card}")
    List<Record> selectUserRecordsByCard(String card);

    /**
     * 以用户名查询学员所有记录
     *
     * @return
     */
    @Select("select r.id, r.stu_card, r.tea_card, r.start_time, r.end_time, r.location, r.status from user, record as r where user.identity=1 and user.name like #{stuName} and user.card=r.stu_card order by end_time desc")
    List<Record> selectStuRecords(String stuName);

    /**
     * 以用户名查询教练所有记录
     *
     * @return
     */
    @Select("select r.id, r.stu_card, r.tea_card, r.start_time, r.end_time, r.location, r.status from user, record as r where user.identity=2 and user.name like #{teaName} and user.card=r.tea_card order by end_time desc")
    List<Record> selectTeaRecords(String teaName);

    //查所有记录按结束时间降序
    @Select("select r.id, r.stu_card, r.tea_card, r.start_time, r.end_time, r.location, r.status from user, record as r where user.identity=1 and user.card=r.stu_card order by end_time desc")
    List<Record> selectAllStuRecords();

//    //查所有教练记录按结束时间降序
//    @Select("select r.id, r.stu_card, r.tea_card, r.start_time, r.end_time, r.location, r.status from user, record as r where user.identity=2 and user.card=r.tea_card order by end_time desc")
//    List<Record> selectAllTeaRecords();

    /**
     * 更改record记录的状态(即教练审核预约申请)
     *
     * @param status
     * @param id
     * @return
     */
    @Update("update record set status=#{status} where id=#{id}")
    boolean updateStatus(@Param("status") int status, @Param("id") int id);

    /**
     * 根据教练card查找未审核预约
     *
     * @param card
     * @return
     */
    @Select("select r.id, r.stu_card, r.tea_card, r.start_time, r.end_time, r.location from user, record as r where user.identity=2 and user.card=#{card} and user.card=r.tea_card and r.status=0 order by r.id")
    List<Record> selectUnaccepted(String card);
}
