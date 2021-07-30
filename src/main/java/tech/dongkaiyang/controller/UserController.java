package tech.dongkaiyang.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import tech.dongkaiyang.domain.Record;
import tech.dongkaiyang.domain.User;
import tech.dongkaiyang.service.RecordService;
import tech.dongkaiyang.service.UserService;

import javax.imageio.ImageIO;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Random;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private RecordService recordService;

    @Autowired
    private JavaMailSender mailSender;

    /**
     * 检测身份证或邮箱是否可用
     * 测试
     *
     * @param s
     * @return
     */
    @RequestMapping(value = "/verify/check", method = RequestMethod.POST)
    @ResponseBody
    public boolean check(@RequestParam("s") String s) {
        return userService.verify(s) == 0;
    }

    /**
     * 发送验证邮件
     * JUnit测试
     *
     * @param
     * @return
     */
    @RequestMapping("/verify/sendEmail")
    @ResponseBody
    public String sendEmail(@RequestParam("email") String email, HttpSession session) throws MessagingException {
        System.out.println(mailSender == null);
        StringBuilder code = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < 6; i++) {
            code.append(random.nextInt(9));
        }
        System.out.println(code);
        session.setAttribute("code", code.toString());

        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper;
        helper = new MimeMessageHelper(message, true);
        helper.setSubject("注册验证码");
        helper.setText("你的注册验证码为：" + code.toString() + " ，请尽快验证", false);
        helper.setTo(email);
        helper.setFrom("921197494@qq.com");
        mailSender.send(message);

        return "邮件发送成功";
    }


    /**
     * 注册页面
     * 数据库测试
     *
     * @param user
     * @return
     */
    // TODO 注册成功后重定向到登录页面
    @RequestMapping(value = "/verify/signUp", method = RequestMethod.POST)
    @ResponseBody
    public boolean signUp(@RequestBody User user, HttpSession session, @RequestParam("code") String userCode) {
        if (user.getIdentity() == 1) {
            user.setRank(0);
        }
        String code = (String) session.getAttribute("code");
        return (userCode.equalsIgnoreCase(code) && userService.insertUser(user));
    }

    /**
     * 登陆进入功能页面
     *
     * @param user
     * @return
     */
    @RequestMapping(value = "/verify/login", method = RequestMethod.POST)
    public String login(@RequestBody User user, HttpSession session) {
        User dbUser = userService.queryUser(user);
        if (dbUser == null) {
            session.setAttribute("msg", "身份证或密码错误");
            return "index";
        }
        dbUser.setPassword(null);
        session.setAttribute("user", dbUser);

        switch (dbUser.getIdentity()) {
            case 1:
                return "studentIndex";
            case 2:
                return "teacherIndex";
            case 4:
                return "adminIndex";
        }
        return "404";
    }

    /**
     * 根据用户身份证查询学员所有学习记录、教练所有上课记录
     * 测试
     *
     * @param session
     * @return
     */
    // TODO 查找用户所属记录、修改路径名、修改传参
    @PostMapping("/findUserRecords")
    @ResponseBody
    public List<Record> findRecords(HttpSession session) {
        String card = ((User) session.getAttribute("user")).getCard();
        List<Record> records = recordService.findUserRecords(card);
        session.setAttribute("records", records);
        return records;
    }


    /**
     * 学员查看教练名单
     * 测试
     *
     * @param request
     * @return
     */
    // TODO 查看可用教练名单
    @PostMapping("/stu/available")
    @ResponseBody
    public List<User> getAvailable(HttpServletRequest request) {
        List<User> available = userService.findAvailable();
        request.getSession().setAttribute("available", available);
        return available;
    }

    /**
     * 学员提交申请
     * 测试
     *
     * @param record
     * @return
     */
    // TODO 学员提交申请、检测时间先后正确
    @PostMapping("/stu/apply")
    @ResponseBody
    public boolean submit(@RequestBody Record record) {
        record.setStatus(0);
        return recordService.insertRecord(record);
    }

    /**
     * 教练查看未审核请求
     * 测试
     *
     * @param card
     * @return
     */
    @RequestMapping("/tea/search")
    @ResponseBody
    public List<Record> search(@RequestParam("card") String card) {
        return userService.searchRecord(card);
    }

    /**
     * 教练通过recordId审核学员申请，申请目标状态为status
     * 测试
     *
     * @param id
     * @param status
     * @return
     */
    // TODO 教练接受请求
    @RequestMapping("/tea/accept")
    @ResponseBody
    public boolean acceptApply(@RequestParam("status") int status, @RequestParam("id") int id) {
        return recordService.updateStatus(status, id);
    }

    /**
     * 登出
     *
     * @param request
     * @return
     */
    @RequestMapping("/logout")
    public String logout(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.removeAttribute("user");
        return "quit";
    }


    /**
     * 管理员按用户名查询学员记录
     * 测试
     *
     * @return
     */
    @PostMapping("/admin/findAllStuRecords")
    @ResponseBody
    public List<Record> findAllStuRecords(@RequestParam("stuName") String stuName) {
        return recordService.findAllStuRecords("%" + stuName + "%");
    }

    /**
     * 管理员按用户名查询教练记录
     * 测试
     *
     * @return
     */
    @PostMapping("/admin/findAllTeaRecords")
    @ResponseBody
    public List<Record> findAllTeaRecords(@RequestParam("teaName") String teaName) {
        return recordService.findAllTeaRecords("%" + teaName + "%");
    }


    /**
     * 管理员改变教练等级
     * 测试
     *
     * @param card
     * @param rank
     * @return
     */
    @PostMapping("/admin/changeRank")
    @ResponseBody
    public boolean changeRank(@RequestParam("card") String card, @RequestParam("rank") int rank) {
        return userService.changeRank(card, rank);
    }

    /**
     * 管理员查看未审核的教练注册名单
     * 测试
     *
     * @return
     */
    @PostMapping("/admin/findRegisterTea")
    @ResponseBody
    public List<User> findRegisterTea() {
        return userService.findRegisterTea();
    }

    /**
     * 管理员审核教练注册
     * 测试
     *
     * @param card
     * @param identity
     * @return
     */
    @PostMapping("/admin/accept")
    @ResponseBody
    public boolean adminAccept(@RequestParam("card") String card, @RequestParam("identity") int identity) {
        if (identity == 2) {
            return userService.changeIdentity(card, identity) && userService.changeRank(card, 1);
        } else {
            return userService.changeIdentity(card, 1);
        }
    }




}
