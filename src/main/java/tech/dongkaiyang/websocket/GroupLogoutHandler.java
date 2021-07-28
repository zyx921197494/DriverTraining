package tech.dongkaiyang.websocket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;
import tech.dongkaiyang.domain.User;
import tech.dongkaiyang.model.GroupOnlineMap;

import java.util.Map;

@Component
public class GroupLogoutHandler implements ApplicationListener<SessionDisconnectEvent> {
    @Autowired
    private SimpMessagingTemplate messagingTemplate;
    @Autowired
    private GroupOnlineMap groupOnlineMap;
    @Override
    public void onApplicationEvent(SessionDisconnectEvent event) {
            Map<String, User> activeSessions = groupOnlineMap.getActiveSessions();
            StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(event.getMessage());
            Map<String, Object> sessionAttributes = headerAccessor.getSessionAttributes();
            User disconnectSession = (User) sessionAttributes.get("user");
            String disconnectUserName = disconnectSession.getName();
            if(groupOnlineMap.containsUserName(disconnectUserName)){
                User removeUser = groupOnlineMap.remove(disconnectUserName);
                messagingTemplate.convertAndSend("/topic/logout",removeUser);
            }
        }
}
