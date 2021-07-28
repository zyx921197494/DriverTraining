package tech.dongkaiyang.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.dongkaiyang.dao.RecordDao;
import tech.dongkaiyang.domain.Record;
import tech.dongkaiyang.domain.User;
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
    public List<Record> findAllRecords(String name) {
        return recordDao.findRecordsByName(name);
    }

    @Override
    public List<Record> findAll() {
        return recordDao.findAll();
    }

    @Override
    public boolean updateStatus(int id) {
        return recordDao.updateStatus(id);
    }


}
