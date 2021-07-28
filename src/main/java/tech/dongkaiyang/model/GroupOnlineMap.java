package tech.dongkaiyang.model;

import org.springframework.stereotype.Component;
import tech.dongkaiyang.domain.User;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class GroupOnlineMap {
    private Map<String, User> activeSessions = new ConcurrentHashMap<String, User>();

    public void add(String name,User user){
        activeSessions.put(name, user);
    }

    public User remove(String name){
        return activeSessions.remove(name);
    }

    public boolean containsUserName(String name){
        return activeSessions.containsKey(name);
    }

    public Map<String, User> getActiveSessions() {
        return activeSessions;
    }

    public void setActiveSessions(Map<String, User> activeSessions) {
        this.activeSessions = activeSessions;
    }
}
