package tech.dongkaiyang.dao;

import org.apache.ibatis.annotations.Insert;
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
    @Insert("insert into record (stu_id, tea_id, start_time, end_time , location, status) values (#{stuId}, #{teaId}, #{startTime}, #{endTime}, #{location}), #{status}")
    boolean insertRecord(Record record);

    /**
     * 由用户名查询记录
     *
     * @param name
     * @return
     */
    @Select("select * from record where name=#{name}")
    List<Record> findRecordsByName(String name);

    /**
     * 查询所有记录
     * @return
     */
    @Select("select * from record order by end_time desc;")
    List<Record> findAll();


    @Update("update record set status=1 where id=#{recordId}")
    boolean updateStatus(int recordId);

}
