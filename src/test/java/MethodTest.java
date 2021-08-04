import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;

import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import tech.dongkaiyang.service.RecordService;
import tech.dongkaiyang.service.UserService;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;

/**
 * @ClassName Test
 * @Description
 * @Author zyx
 * @Date 2021-07-29 18:24
 * @Blog www.winkelblog.top
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/applicationContext.xml"})
public class MethodTest {

    @Resource
    private JavaMailSender mailSender;

    @Autowired
    UserService userService;

    @Autowired
    RecordService recordService;


    @Test
    public void check() {
//        User user = new User(2222, "342","dfioasdjfio","aaaaa","123",1,0);
//        System.out.println(userService.insertUser(user));

//        Record record = new Record(6, "1", "3", LocalDateTime.now(), LocalDateTime.of(2021, 7, 20, 12, 30, 30), "训练场地", 0);
//        record.setStatus(0);
//        System.out.println(recordService.insertRecord(record));

//        System.out.println(recordService.updateStatus(1, 6));

        System.out.println(recordService.findUserRecords("1"));
//        System.out.println(recordService.findAllTeaRecords("%" + "c" + "%"));
//        System.out.println(userService.changeRank("3", 2));

//        System.out.println(userService.findRegisterTea());


//        int identity = 3;
//        String card = "342";
//        if (identity == 2) {
//            System.out.println(userService.changeIdentity(card, identity) && userService.changeRank(card, 1));
//        } else {
//            System.out.println(userService.changeIdentity(card, 1));
//        }


//        System.out.println(recordService.findUserRecords("4"));

//        User user = new User();
//        user.setCard("1");
//        System.out.println(userService.queryUser(user));
    }

    @Test
    public void dateTimeTest() {
        String s = "2021-07-30 11:10:11";
        String[] ss = s.split(" ");
        System.out.println("ss = " + ss);

        LocalDateTime localDateTime = LocalDateTime.parse(ss[0] + "T" + ss[1], DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        System.out.println("localDateTime = " + localDateTime);
    }


    @Test
    public void sendEmail() {
        System.out.println(mailSender == null);
        StringBuilder code = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < 6; i++) {
            code.append(random.nextInt(9));
        }
        System.out.println(code.toString());
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = null;
        try {
            helper = new MimeMessageHelper(message, true);
            helper.setSubject("注册验证码");
            helper.setText("你的注册验证码为：" + code.toString() + " 请尽快验证", false);
            helper.setTo("winkelzyx@163.com");
            helper.setFrom("921197494@qq.com");
            mailSender.send(message);
        } catch (MessagingException e) {
            System.out.println(e.getMessage());
            System.out.println(e.getCause());
            e.printStackTrace();
        }
    }
}
