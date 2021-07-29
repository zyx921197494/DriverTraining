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

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
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
     *
     * @param s
     * @return
     */
    @RequestMapping(value = "/verify/check", method = RequestMethod.POST)
    @ResponseBody
    public boolean check(@RequestParam("s") String s) {
        return userService.verify(s);
    }

    /**
     * 注册时发送验证邮件
     *
     * @param
     * @return
     */
    @RequestMapping("/verify/sendEmail")
    @ResponseBody
    public String sendEmail(@RequestParam("email") String email) throws MessagingException {
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
            helper.setTo(email);
            helper.setFrom("921197494@qq.com");
            mailSender.send(message);
        } catch (MessagingException e) {
            System.out.println(e.getCause());
            System.out.println(e.getMessage());
            e.printStackTrace();
        } finally {
            userService.insertCode(code.toString());
        }

        return "邮件发送成功";
    }

    /**
     * 校验验证码合法性
     *
     * @param code
     * @return
     */
    @RequestMapping("/verify/code")
    @ResponseBody
    public boolean verifyCode(@RequestParam String code) {
        boolean success = false;
        if (userService.validateCode(code)) {
            if (userService.deleteCode(code)) {
                success = true;
            }
        }
        return success;
    }

    @RequestMapping("/verify/getImage")
    public String getImage() {
        return "";
    }

    @RequestMapping("/verify/image")
    public String verifyImage() {
        return "";
    }


    /**
     * 注册页面
     *
     * @param user
     * @return
     */
    @RequestMapping(value = "/verify/signUp", method = RequestMethod.POST)
    @ResponseBody
    public boolean signUp(@RequestBody User user) {
        if (user.getIdentity() == 1) {
            user.setRank(0);
        }
        return userService.insertUser(user);
    }

    /**
     * 登陆进入功能页面
     *
     * @param user
     * @return
     */
    @RequestMapping(value = "/verify/login", method = RequestMethod.POST)
    public String menuPage(@RequestBody User user, HttpServletRequest request) {
        boolean exist = userService.queryUser(user);
        if (!exist) {
            request.setAttribute("msg", "身份证或密码错误");
            return "login";
        }
        user.setPassword(null);
        HttpSession session = request.getSession();
        session.setAttribute("user", user);

        switch (user.getIdentity()) {
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
     *
     * @param request
     * @return
     */
    // TODO 查找用户所属记录、修改路径名、修改传参
    @PostMapping("/findRecords")
    @ResponseBody
    public List<Record> findRecords(HttpServletRequest request) {
        HttpSession session = request.getSession();
        String card = ((User) session.getAttribute("user")).getCard();
        List<Record> records = recordService.findAllRecords(card);
        session.setAttribute("records", records);
        return records;
    }

    /**
     * 管理员查询所有记录
     *
     * @return
     */
    // TODO 管理员查询所有记录
    @PostMapping("/findAll")
    @ResponseBody
    public List<Record> findAllRecords() {
        return recordService.findAll();
    }

    /**
     * 根据身份证查找
     *
     * @param request
     * @return
     */
//    // TODO 根据用户名name查找用户id，用于申请的提交与接收
//    @PostMapping("/findId")
//    public int findUserIdByName(HttpServletRequest request) {
//        String name = (String) request.getSession().getAttribute("name");
//        return userService.queryUserId(name).getId();
//    }

    /**
     * 学员查看教练名单
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
     *
     * @param id
     * @param status
     * @return
     */
    // TODO 教练接受请求
    @RequestMapping("/tea/accept")
    @ResponseBody
    public boolean acceptApply(@RequestParam("id") int id, @RequestParam("status") int status) {
        return recordService.updateStatus(id, status);
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
     * 管理员改变教练等级
     *
     * @param card
     * @param rank
     * @return
     */
    @PostMapping("/admin/changeRank")
    public boolean changeRank(@RequestParam("card") String card, @RequestParam("rank") int rank) {
        return userService.changeRank(card, rank);
    }

    /**
     * 管理员审核教练注册
     *
     * @param card
     * @param identity
     * @return
     */
    @PostMapping("/admin/accept")
    public boolean accept(@RequestParam("card") String card, @RequestParam("identity") int identity) {
        return userService.changeIdentity(card, identity);
    }

}
