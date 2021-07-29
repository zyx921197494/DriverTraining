import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.Random;

/**
 * @ClassName Test
 * @Description
 * @Author zyx
 * @Date 2021-07-29 18:24
 * @Blog www.winkelblog.top
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:applicationContext.xml"})
public class Test {

    @Resource
    private JavaMailSender mailSender;

    @org.junit.Test
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
            System.out.println(e.getCause());
            System.out.println(e.getMessage());
            e.printStackTrace();
        }

//        return "邮件发送成功";
    }
}
