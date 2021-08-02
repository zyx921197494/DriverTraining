package tech.dongkaiyang.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.dongkaiyang.dao.RecordDao;
import tech.dongkaiyang.domain.Record;
import tech.dongkaiyang.service.RecordService;

import java.util.List;

@Service
public class RecordServiceImpl implements RecordService {

    @Autowired
    private RecordDao recordDao;

    @Override
    public boolean insertRecord(Record record) {
        return recordDao.insertRecord(record);
    }

    @Override
    public List<Record> findUserRecords(String card) {
        return recordDao.selectUserRecordsByCard(card);
    }

    @Override
    public List<Record> findAllStuRecords(String stuName) {
        return recordDao.selectStuRecords(stuName);
    }

    @Override
    public List<Record> findStuRecords() {
        return recordDao.selectAllStuRecords();
    }

    @Override
    public List<Record> findAllTeaRecords(String teaName) {
        return recordDao.selectTeaRecords(teaName);
    }

//    @Override
//    public List<Record> findTeaRecords() {
//        return recordDao.selectAllTeaRecords();
//    }

    @Override
    public boolean updateStatus(int status, int id) {
        return recordDao.updateStatus(status, id);
    }


}
