package tech.dongkaiyang.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import sun.util.resources.LocaleData;
import tech.dongkaiyang.domain.Record;
import tech.dongkaiyang.service.RecordService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Controller
public class RecordController {

    @Autowired
    private SimpMessagingTemplate messagingTemplate;
    @Autowired
    private RecordService recordService;
    /**
     * 接收并转发信息
     * @param record
     */
    @MessageMapping("/chat/message")
    public void receiveMessage(Record record){
//        record.setSendDate();
//        recordService.insertMessage(record);
//        messagingTemplate.convertAndSend("/topic/chat/"+destination, record);
    }
}
