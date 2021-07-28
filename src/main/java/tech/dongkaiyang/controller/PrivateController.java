package tech.dongkaiyang.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import tech.dongkaiyang.domain.User;
import tech.dongkaiyang.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class PrivateController {
    @Autowired
    private UserService userService;
    /**
     * 选择用户
     * @return
     */
    @RequestMapping(value = "/privateChatTest")
    public String privateChat(HttpServletRequest request){
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        List<String> userList = userService.findAllUserNames();
        session.setAttribute("userList",userList);
        return "OnlineFriends";
    }

    /**
     * 进入房间
     * @return
     */
    @RequestMapping(value = "/privateChatRoom")
    public String privateChatRoom(String destination,HttpServletRequest request){
        HttpSession session = request.getSession();
        session.setAttribute("destination",destination);
        return "privateChat";
    }
}
