package tech.dongkaiyang.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import tech.dongkaiyang.domain.Record;
import tech.dongkaiyang.domain.Result;
import tech.dongkaiyang.domain.User;
import tech.dongkaiyang.service.RecordService;
import tech.dongkaiyang.service.UserService;
import tech.dongkaiyang.vo.RecordVo;


import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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

        System.out.println(code.toString());
        return code.toString();
    }


    /**
     * 注册页面
     * 数据库测试
     *
     * @param user
     * @return
     */
    // TODO 注册成功后到登录页面
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
    @ResponseBody
    public Result login(@RequestBody User user, HttpServletRequest request) throws IOException, ServletException {
        User dbUser = userService.queryUser(user.getCard(), user.getPassword());
        HttpSession session = request.getSession();
        if (dbUser == null) {
            return new Result("0", "http://localhost:8080/index.html");
        }
        dbUser.setPassword(null);
        session.setAttribute("user", dbUser);

        switch (dbUser.getIdentity()) {
            case 1:
                return new Result("1", "http://localhost:8080/studentIndex.html");
            case 2:
                return new Result("2", "http://localhost:8080/teacherIndex.html");
            case 4:
                return new Result("4", "http://localhost:8080/adminIndex.html");
            default:
                return new Result("0", "http://localhost:8080/index.html");
        }
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
    public Result<List<Record>> findRecords(HttpSession session) {
        String card = ((User) session.getAttribute("user")).getCard();
        System.out.println("card = " + card);
        List<Record> records = recordService.findUserRecords(card);
        if (records.isEmpty()) {
            return Result.fail("查询记录失败");
        }
        session.setAttribute("records", records);
        return Result.success("查询记录成功", records);
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
    public Result<List<User>> getAvailable(HttpServletRequest request) {
        List<User> available = userService.findAvailable();
        if (available.isEmpty()) {
            return Result.fail("教练名单获取失败");
        }
        request.getSession().setAttribute("available", available);
        return Result.success("获取教练名单成功", available);
    }

    /**
     * 学员提交申请
     * 测试
     *
     * @param recordVo
     * @return
     */
    // TODO 学员提交申请、检测时间先后正确
    @PostMapping("/stu/apply")
    @ResponseBody
    public Result submit(@RequestBody RecordVo recordVo, HttpSession session) {

        User user = getSessionUser(session);
        if (user == null) {
            return Result.fail("未登录，请先登录");
        }
        String[] start = recordVo.getStartTime().split(" ");
        String[] end = recordVo.getEndTime().split(" ");

        Record record = new Record();
        LocalDateTime s = LocalDateTime.parse(start[0] + "T" + start[1], DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        LocalDateTime e = LocalDateTime.parse(end[0] + "T" + end[1], DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        if (!e.isAfter(s)) {
            return Result.fail("训练日期时间填写有误");
        }
        record.setStartTime(s);
        record.setEndTime(e);

        record.setStuCard(user.getCard());
        record.setTeaCard(recordVo.getTeaCard());
        record.setLocation(recordVo.getLocation());
        record.setStatus(0);

        boolean success = recordService.insertRecord(record);
        if (success) {
            return Result.success("提交预约申请成功");
        } else return Result.fail("提交预约申请失败");
    }

    /**
     * 教练查看未审核请求
     * 测试
     *
     * @param session
     * @return
     */
    @PostMapping("/tea/search")
    @ResponseBody
    public Result<List<Record>> search(HttpSession session) {
        User user = getSessionUser(session);
        List<Record> records = userService.searchRecord(user.getCard());
        if (!records.isEmpty()) {
            return Result.success("查询成功", records);
        } else return Result.fail("未查找到未审核预约");
    }

    /**
     * 教练通过recordId审核学员申请，申请目标状态为status
     * 测试
     *
     * @param status
     * @param id
     * @return
     */
    // TODO 教练接受请求
    @GetMapping("/tea/accept")
    @ResponseBody
    public Result acceptApply(@RequestParam("status") int status, @RequestParam("id") int id) {
        boolean success = recordService.updateStatus(status, id);
        if (success) {
            return Result.success("接受预约成功");
        } else return Result.fail("接受预约失败");
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
     * 管理员查所有记录
     *
     * @return
     */
    @PostMapping("/admin/findAllRecords")
    @ResponseBody
    public Result<List<Record>> findAllRecords() {
        List<Record> records = recordService.findStuRecords();
        if (records.isEmpty()) {
            Result.fail("未查找到记录");
        }
        return Result.success("查询学员记录成功", records);
    }

//    /**
//     * 管理员查所有教练记录
//     * @return
//     */
//    @PostMapping("/admin/findAllTeaRecords")
//    @ResponseBody
//    public Result<List<Record>> findAllTeaRecords() {
//        List<Record> records = recordService.findTeaRecords();
//        if (records.isEmpty()) {
//            Result.fail("未查找到记录");
//        }
//        return Result.success("查询教练成功", records);
//    }

    /**
     * 管理员按用户名查询学员记录
     * 测试
     *
     * @param stuName
     * @return
     */
    @PostMapping("/admin/findAllStuRecordsByName")
    @ResponseBody
    public Result<List<Record>> findAllStuRecordsByName(@RequestParam("stuName") String stuName) {
        List<Record> records = recordService.findAllStuRecords("%" + stuName + "%");
        if (records.isEmpty()) {
            Result.fail("未查找到记录");
        }
        return Result.success("查询成功", records);
    }

    /**
     * 管理员按用户名查询教练记录
     * 测试
     *
     * @return
     */
    @PostMapping("/admin/findAllTeaRecordsByName")
    @ResponseBody
    public Result<List<Record>> findAllTeaRecordsByName(@RequestParam("teaName") String teaName) {
        List<Record> records = recordService.findAllTeaRecords("%" + teaName + "%");
        if (records.isEmpty()) {
            return Result.fail("查询教练记录失败");
        } else return Result.success("查找教练记录成功", records);
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
    public Result changeRank(@RequestParam("card") String card, @RequestParam("rank") int rank) {
        boolean success = userService.changeRank(card, rank);
        if (success) {
            return Result.success("更改教练等级成功");
        }
        return Result.fail("更改教练等级失败");
    }

    /**
     * 管理员查看未审核的教练注册名单
     * 测试
     *
     * @return
     */
    @PostMapping("/admin/findRegisterTea")
    @ResponseBody
    public Result<List<User>> findRegisterTea() {
        List<User> teas = userService.findRegisterTea();
        if (teas.isEmpty()) {
            return Result.fail("未查到未审核的教练注册申请");
        }
        return Result.success("查询成功", teas);
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
    public Result adminAccept(@RequestParam("card") String card, @RequestParam("identity") int identity) {
        if (identity == 2 && userService.changeIdentity(card, identity) && userService.changeRank(card, 1)) {
            return Result.success("提交成功，教练申请通过");
        } else if (identity == 3 && userService.changeIdentity(card, 1)) {
            return Result.success("提交成功，教练申请未通过");
        }
        return Result.fail("审核提交失败");
    }

    @PostMapping("/getCurrentUser")
    @ResponseBody
    public User getCurrentUser(HttpSession session) {
        return (User) session.getAttribute("user");
    }


    public User getSessionUser(HttpSession session) {
        return (User) session.getAttribute("user");
    }

}
