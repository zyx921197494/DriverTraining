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
     * @param card
     * @return
     */
    @Select("select * from record where card=#{card}")
    List<Record> selectRecordsByName(String card);

    /**
     * 查询所有记录
     * @return
     */
    @Select("select * from record order by end_time desc;")
    List<Record> selectAll();


    /**
     * 更改record记录的状态(即教练接受预约申请)
     * @param recordId
     * @param status
     * @return
     */
    @Update("update record set status=#{status} where id=#{recordId}")
    boolean updateStatus(int recordId, int status);

}
