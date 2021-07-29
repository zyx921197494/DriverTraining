package tech.dongkaiyang.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;
import tech.dongkaiyang.domain.Record;
import tech.dongkaiyang.service.RecordService;

@Controller
public class RecordController {


    @Autowired
    private RecordService recordService;

    /**
     *
     * @param record
     */
//    @MessageMapping("/chat/message")
//    public void receiveMessage(Record record){
//
//    }
}
