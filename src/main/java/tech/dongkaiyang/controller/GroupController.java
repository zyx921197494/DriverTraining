package tech.dongkaiyang.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.annotation.SubscribeMapping;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;
import tech.dongkaiyang.domain.User;
import tech.dongkaiyang.model.GroupOnlineMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller
public class GroupController {
    @Autowired
    private SimpMessagingTemplate messagingTemplate;
    @Autowired
    private GroupOnlineMap groupOnlineMap;

    /**
     * 群聊
     *
     * @return
     */
    @RequestMapping(value = "/groupChatTest")
    public String groupChat(HttpServletRequest request) {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        messagingTemplate.convertAndSend("/topic/GroupLogin", user);
        groupOnlineMap.add(user.getName(), user);
        return "groupChat";
    }

    /**
     * 查询在线人数
     *
     * @return
     */
    @SubscribeMapping("/chat/onlineUserNumber")
    public Long getOnlineUserNumber() {
        return Long.valueOf(groupOnlineMap.getActiveSessions().values().size());
    }

    /**
     * 退出群聊
     *
     * @param sessionDisconnectEvent
     */
    public void logout(SessionDisconnectEvent sessionDisconnectEvent) {
        Map<String, User> activeSessions = groupOnlineMap.getActiveSessions();
        StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(sessionDisconnectEvent.getMessage());
        Map<String, Object> sessionAttributes = headerAccessor.getSessionAttributes();
        User disconnectSession = (User) sessionAttributes.get("user");
        String disconnectUserName = disconnectSession.getName();
        if (groupOnlineMap.containsUserName(disconnectUserName)) {
            User removeUser = groupOnlineMap.remove(disconnectUserName);
            messagingTemplate.convertAndSend("/topic/logout", removeUser);
        }
    }

}
