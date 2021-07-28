package tech.dongkaiyang.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import tech.dongkaiyang.domain.Record;
import tech.dongkaiyang.domain.User;
import tech.dongkaiyang.service.RecordService;
import tech.dongkaiyang.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private RecordService recordService;

    /**
     * 检测用户名是否可用
     *
     * @param user
     * @return
     */
    @RequestMapping(value = "/verify/checkName", method = RequestMethod.POST)
    @ResponseBody
    public boolean checkName(@RequestBody User user) {
        return userService.isExistUser(user.getName());
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
        return userService.insertUser(user);
    }

    /**
     * 登陆进入功能页面
     *
     * @param user
     * @return
     */
    @RequestMapping(value = "/menuPageTest", method = RequestMethod.POST)
    public String menuPage(User user, HttpServletRequest request) {
        if (!userService.queryUser(user)) {
            request.setAttribute("msg", "用户名或密码错误");
            return "/#login";
        }
        user.setPassword(null);
        HttpSession session = request.getSession();
        session.setAttribute("user", user);
        return "/#menuPage";
    }

    /**
     * 根据用户名查询学员学习记录、教练上课记录
     *
     * @param request
     * @return
     */
    // TODO 查找用户所属记录、修改路径名、修改传参
    @PostMapping("/findMessageLog")
    public String findMessageLog(HttpServletRequest request) {
        HttpSession session = request.getSession();
        String name = ((User) session.getAttribute("user")).getName();
        List<Record> records = recordService.findAllRecords(name);
        session.setAttribute("messages", records);
        return "#messageLog";
    }

    /**
     * 管理员查询所有记录
     *
     * @return
     */
    // TODO 管理员查询所有记录
    @PostMapping("/findAll")
    public String findRecords() {
        recordService.findAll();
        return "#messageLog";
    }

    /**
     * 根据用户名name查找id用于学员申请
     *
     * @param request
     * @return
     */
    // TODO 根据用户名name查找用户id，用于申请的提交与接收
    @PostMapping("/findId")
    public int findUserIdByName(HttpServletRequest request) {
        String name = (String) request.getSession().getAttribute("name");
        return userService.queryUserId(name).getId();
    }

    /**
     * 学员查看教练名单
     *
     * @param request
     * @return
     */
    // TODO 查看可用教练名单
    @PostMapping("/available")
    public String getAvailable(HttpServletRequest request) {
        List<User> available = userService.findAvailable();
        request.getSession().setAttribute("available", available);
        return "#messageLog";
    }

    /**
     * @param record
     * @return
     */
    // TODO 学员提交申请、jsp检测时间先后正确
    @PostMapping("/submit")
    public String submit(@RequestBody Record record) {
        record.setStatus(0);
        recordService.insertRecord(record);
        return "#messageLog";
    }

    /**
     * 教练接受学员申请
     *
     * @param request
     * @return
     */
    // TODO 教练接受请求
    @RequestMapping("/accept")
    public String accept(HttpServletRequest request) {
        int recordId = (int) request.getSession().getAttribute("id");
        recordService.updateStatus(recordId);
        return "#messageLog";
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
        return "/#quit";
    }

}
